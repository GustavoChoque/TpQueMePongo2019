package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import exceptions.FaltanteDePrendasException;
import servicios.ProveedorClima;

public class Sugeridor {
	List<Prenda> prendasSuperiores;
	List<Prenda> prendasSuperiores2;
	List<Prenda> prendasSuperiores3;
	List<Prenda> prendasInferiores;
	List<Prenda> calzados;
	List<Prenda> accesorios;
	ProveedorClima proveedorClima;
	
	public Sugeridor(ProveedorClima proveedor){
		prendasSuperiores=new ArrayList<Prenda>();
		prendasSuperiores2=new ArrayList<Prenda>();
		prendasSuperiores3=new ArrayList<Prenda>();
		prendasInferiores=new ArrayList<Prenda>();
		calzados=new ArrayList<Prenda>();
		accesorios=new ArrayList<Prenda>();
		this.proveedorClima =proveedor;
		
		
	}
	
	private void separarPrendas(List<Prenda> prendas){
		prendasSuperiores=prendas.stream()
				.filter(p->p.getCategoria().equals(Categoria.PARTE_SUPERIOR)&& p.getCapa()==1)
				.collect(Collectors.toList());
		prendasSuperiores2=prendas.stream()
				.filter(p->p.getCategoria().equals(Categoria.PARTE_SUPERIOR)&& p.getCapa()==2)
				.collect(Collectors.toList());
		prendasSuperiores2.add(new Prenda(new TipoDePrenda(Categoria.PARTE_SUPERIOR,"SinSuperior",2,0),Color.NINGUNO,Tela.NINGUNO));
		
		prendasSuperiores3=prendas.stream()
				.filter(p->p.getCategoria().equals(Categoria.PARTE_SUPERIOR) && p.getCapa()==3)
				//.filter(p->p.getCapa()==3)
				.collect(Collectors.toList());
		prendasSuperiores3.add(new Prenda(new TipoDePrenda(Categoria.PARTE_SUPERIOR,"SinSuperior",2,0),Color.NINGUNO,Tela.NINGUNO));
		
		prendasInferiores=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_INFERIOR)).collect(Collectors.toList());
		calzados=prendas.stream().filter(p->p.getCategoria().equals(Categoria.CALZADO)).collect(Collectors.toList());
		accesorios=prendas.stream().filter(p->p.getCategoria().equals(Categoria.ACCESORIO)).collect(Collectors.toList());
		accesorios.add(new Prenda(new TipoDePrenda(Categoria.ACCESORIO, "SinAccesorio",1,0),Color.NINGUNO,Tela.NINGUNO));

	}
	
	
	//Esto es para sugerir en el dia 
	public List<Atuendo> sugerir(List<Prenda> prendas,Usuario usuario){
		
				separarPrendas(prendas);
				
				List<Atuendo> aux;
				if(puedeGenerarSugerencia()) {
					aux= Lists
							.cartesianProduct(prendasSuperiores,prendasSuperiores2,prendasSuperiores3,prendasInferiores,calzados,accesorios)
							.stream()
							.map((list)->new Atuendo(list.get(0),list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)))
							.collect(Collectors.toList());	
					}else{
						throw new FaltanteDePrendasException("Debe tener al menos una prenda inferior, superior y calzado para generar sugerencia");
					}
				
				return filtrarPorUnaTemperatura(filtrarRedundancias(aux),proveedorClima.getTemperatura(),usuario);
			}
	
	
	// esto es para sugerir en una fecha
	public List<Atuendo> sugerir(LocalDate fecha,List<Prenda> prendas,Usuario usuario){
		List<Atuendo> aux;
		Double temperatura=this.proveedorClima.getTemperaturaDeUnaFecha(fecha);
		//filtrarPrendas(temperatura);
		separarPrendas(prendas);
		if(puedeGenerarSugerencia()) {
			aux= Lists
				.cartesianProduct(prendasSuperiores,prendasSuperiores2,prendasSuperiores3,prendasInferiores,calzados,accesorios)
				.stream()
				.map((list)->new Atuendo(list.get(0),list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)))
				.collect(Collectors.toList());	
		}else{
			throw new FaltanteDePrendasException("Debe tener al menos una prenda inferior, superior y calzado para generar sugerencia");
		}
		
		return filtrarPorUnaTemperatura(filtrarRedundancias(aux),temperatura,usuario);
	}
	
	public boolean puedeGenerarSugerencia() {
		return !(prendasSuperiores.isEmpty() || prendasInferiores.isEmpty() || calzados.isEmpty());
	}
	
	public List<Atuendo> filtrarRedundancias(List<Atuendo> atuendos){
		//filtra que no se repitan prendas en las superposiciones,
		//como camisa y arriba otra camisa(pedido de enunciado)
		return atuendos.stream()
				.filter(a->!a.getPrendaSuperior().getTipoDePrenda().equals(a.getPrendaSuperior2().getTipoDePrenda()))
				.filter(a->!a.getPrendaSuperior().getTipoDePrenda().equals(a.getPrendaSuperior3().getTipoDePrenda()))
				.collect(Collectors.toList());
	}
	
	public List<Atuendo> filtrarPorUnaTemperatura(List<Atuendo> atuendos,double temp,Usuario usuario){
		return atuendos.stream()
				.filter(a->criterio(a,temp,usuario))
				.collect(Collectors.toList());
	}
	//mi rango de temperaturas -10 a 50
	//(50-nivelabrigoAtuendo)+-10, la temperatura esta en ese rango => ok
	//por ahora 50 es el nivel maximo de abrigo con los valores que le di
	//a los tipoDeprenda en el Main
	public boolean criterio(Atuendo atuendo,double temp,Usuario usuario){
		return (50-atuendo.getNivelDeAbrigo()*usuario.getNivelFriolencia()-10)<=temp && (50-atuendo.getNivelDeAbrigo()*usuario.getNivelFriolencia()+10)>=temp;
	}	
	
	
}
