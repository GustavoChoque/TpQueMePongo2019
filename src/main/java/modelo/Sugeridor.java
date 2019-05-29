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
	List<Prenda> prendasSuperioresCapa1;//remeras
	List<Prenda> prendasSuperioresCapa2;//camisas
	List<Prenda> prendasSuperioresCapa3;//buzo, sweater
	List<Prenda> prendasSuperioresCapa4;//campera de invierno
	List<Prenda> prendasInferiores;
	List<Prenda> calzados;
	List<Prenda> accesorios;
	ProveedorClima proveedorClima;
	
	public Sugeridor(ProveedorClima proveedor){
		prendasSuperioresCapa1=new ArrayList<Prenda>();
		prendasSuperioresCapa2=new ArrayList<Prenda>();
		prendasSuperioresCapa3=new ArrayList<Prenda>();
		prendasSuperioresCapa4=new ArrayList<Prenda>();
		prendasInferiores=new ArrayList<Prenda>();
		calzados=new ArrayList<Prenda>();
		accesorios=new ArrayList<Prenda>();
		this.proveedorClima =proveedor;
		
		
	}
	
	private void separarPrendas(List<Prenda> prendas){
		prendasSuperioresCapa1=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_SUPERIOR)&p.getCapa()==1).collect(Collectors.toList());
		prendasSuperioresCapa2=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_SUPERIOR)&p.getCapa()==2).collect(Collectors.toList());
		prendasSuperioresCapa3=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_SUPERIOR)&p.getCapa()==3).collect(Collectors.toList());
		prendasSuperioresCapa4=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_SUPERIOR)&p.getCapa()==4).collect(Collectors.toList());
		prendasInferiores=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_INFERIOR)).collect(Collectors.toList());
		calzados=prendas.stream().filter(p->p.getCategoria().equals(Categoria.CALZADO)).collect(Collectors.toList());
		accesorios=prendas.stream().filter(p->p.getCategoria().equals(Categoria.ACCESORIO)).collect(Collectors.toList());
		accesorios.add(new Prenda(new TipoDePrenda(Categoria.ACCESORIO, "SinAccesorio",0,0),Color.NINGUNO,Tela.NINGUNO));

	}
	
	
	//Esto es para sugerir en el dia 
	public List<Atuendo> sugerir(List<Prenda> prendas){
		
		List<Atuendo> aux;
		separarPrendas(prendas);
		if(puedeGenerarSugerencia()) {
			aux= this.generarSugerencias(prendas);	
		}else{
			throw new FaltanteDePrendasException("Debe tener al menos una prenda inferior, superior y calzado para generar sugerencia");
		}
		//filtrarPorUnaTemperatura(aux,temperatura);
		return aux;
	}
	
	
	// esto es para sugerir en una fecha
	public List<Atuendo> sugerir(LocalDate fecha,List<Prenda> prendas){
		
		Double temperatura=this.proveedorClima.getTemperaturaDeUnaFecha(fecha);
		
		List<Atuendo> aux;
		separarPrendas(prendas);
		if(puedeGenerarSugerencia()) {
			aux=this.generarSugerencias(prendas);	
		}else{
			throw new FaltanteDePrendasException("Debe tener al menos una prenda inferior, superior y calzado para generar sugerencia");
		}
		//filtrarPorUnaTemperatura(aux,temperatura);
		return aux;
	}
	
	public boolean puedeGenerarSugerencia() {
		return !((prendasSuperioresCapa1.isEmpty()&& prendasSuperioresCapa2.isEmpty()) || prendasInferiores.isEmpty() || calzados.isEmpty());
	}
	
	public List<Atuendo> generarSugerencias(List<Prenda> prendas){
		List<Atuendo> atuendosCapa1 = generarAtuendosConCapasSup(prendasSuperioresCapa1);
		List<Atuendo> atuendosCapa2 = generarAtuendosConCapasSup(prendasSuperioresCapa2);
		List<Atuendo> atuendosCapas12 = generarAtuendosConCapasSup(prendasSuperioresCapa1,prendasSuperioresCapa2);
		List<Atuendo> atuendosCapas13 =  generarAtuendosConCapasSup(prendasSuperioresCapa1,prendasSuperioresCapa3);
		List<Atuendo> atuendosCapas14 =  generarAtuendosConCapasSup(prendasSuperioresCapa1,prendasSuperioresCapa4);
		List<Atuendo> atuendosCapas24 =  generarAtuendosConCapasSup(prendasSuperioresCapa2,prendasSuperioresCapa4);
		List<Atuendo> atuendosCapas23 =  generarAtuendosConCapasSup(prendasSuperioresCapa2,prendasSuperioresCapa3);
		List<Atuendo> atuendosCapas123 =  generarAtuendosConCapasSup(prendasSuperioresCapa1,prendasSuperioresCapa2,prendasSuperioresCapa3);
		List<Atuendo> atuendosCapas134 =  generarAtuendosConCapasSup(prendasSuperioresCapa1,prendasSuperioresCapa3,prendasSuperioresCapa4);
		List<Atuendo> atuendosCapas124 = generarAtuendosConCapasSup(prendasSuperioresCapa1,prendasSuperioresCapa2,prendasSuperioresCapa4);
		List<Atuendo> atuendosCapas234 = generarAtuendosConCapasSup(prendasSuperioresCapa2,prendasSuperioresCapa3,prendasSuperioresCapa4);
		List<Atuendo> atuendosCapas1234 = generarAtuendosConCapasSup(prendasSuperioresCapa1,prendasSuperioresCapa2,prendasSuperioresCapa3,prendasSuperioresCapa4);
		
		
		List<Atuendo> atuendosTotales = new ArrayList<Atuendo>();
		atuendosTotales.addAll(atuendosCapa1);
		atuendosTotales.addAll(atuendosCapa2);
		atuendosTotales.addAll(atuendosCapas12);
		atuendosTotales.addAll(atuendosCapas13);
		atuendosTotales.addAll(atuendosCapas14);
		atuendosTotales.addAll(atuendosCapas24);
		atuendosTotales.addAll(atuendosCapas23);
		atuendosTotales.addAll(atuendosCapas123);
		atuendosTotales.addAll(atuendosCapas124);
		atuendosTotales.addAll(atuendosCapas134);
		atuendosTotales.addAll(atuendosCapas234);
		atuendosTotales.addAll(atuendosCapas1234);
		return atuendosTotales;
	}
	
	public List<Atuendo> generarAtuendosConCapasSup(List<Prenda> conjuntoCapas1){
		return 	Lists
				.cartesianProduct(conjuntoCapas1,prendasInferiores,calzados,accesorios)
				.stream()
				.map((list)->new Atuendo(Arrays.asList(list.get(0)),list.get(1), list.get(2), list.get(3)))
				.collect(Collectors.toList());
	}
	public List<Atuendo> generarAtuendosConCapasSup(List<Prenda> conjuntoCapas1,List<Prenda> conjuntoCapas2){
		return 	Lists
				.cartesianProduct(conjuntoCapas1,conjuntoCapas2,prendasInferiores,calzados,accesorios)
				.stream()
				.map((list)->new Atuendo(Arrays.asList(list.get(0),list.get(1)),list.get(2), list.get(3), list.get(4)))
				.collect(Collectors.toList());
	}
	public List<Atuendo> generarAtuendosConCapasSup(List<Prenda> conjuntoCapas1,List<Prenda> conjuntoCapas2,List<Prenda> conjuntoCapas3){
		return 	Lists
				.cartesianProduct(conjuntoCapas1,conjuntoCapas2,conjuntoCapas3,prendasInferiores,calzados,accesorios)
				.stream()
				.map((list)->new Atuendo(Arrays.asList(list.get(0),list.get(1),list.get(2)),list.get(3), list.get(4), list.get(5)))
				.collect(Collectors.toList());
	}
	public List<Atuendo> generarAtuendosConCapasSup(List<Prenda> conjuntoCapas1,List<Prenda> conjuntoCapas2,List<Prenda> conjuntoCapas3,List<Prenda> conjuntoCapas4){
		return 	Lists
				.cartesianProduct(conjuntoCapas1,conjuntoCapas2,conjuntoCapas3,conjuntoCapas4,prendasInferiores,calzados,accesorios)
				.stream()
				.map((list)->new Atuendo(Arrays.asList(list.get(0),list.get(1),list.get(2),list.get(3)),list.get(4), list.get(5), list.get(6)))
				.collect(Collectors.toList());
	}
	
}
