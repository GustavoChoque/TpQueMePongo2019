package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import exceptions.FaltanteDePrendasException;
import servicios.ProveedorClima;

public class Sugeridor {
	List<Prenda> prendasSuperiores;
	List<Prenda> prendasInferiores;
	List<Prenda> calzados;
	List<Prenda> accesorios;
	ProveedorClima proveedorClima;
	
	public Sugeridor(ProveedorClima proveedor){
		prendasSuperiores=new ArrayList<Prenda>();
		prendasInferiores=new ArrayList<Prenda>();
		calzados=new ArrayList<Prenda>();
		accesorios=new ArrayList<Prenda>();
		this.proveedorClima =proveedor;
		
		
	}
	
	private void separarPrendas(List<Prenda> prendas){
		prendasSuperiores=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_SUPERIOR)).collect(Collectors.toList());
		prendasInferiores=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_INFERIOR)).collect(Collectors.toList());
		calzados=prendas.stream().filter(p->p.getCategoria().equals(Categoria.CALZADO)).collect(Collectors.toList());
		accesorios=prendas.stream().filter(p->p.getCategoria().equals(Categoria.ACCESORIO)).collect(Collectors.toList());
		accesorios.add(new Prenda(new TipoDePrenda(Categoria.ACCESORIO, "SinAccesorio"),Color.NINGUNO,Tela.NINGUNO));

	}
	
	//Esto es para sugerir en el dia 
	public List<Atuendo> sugerir(List<Prenda> prendas){
		//filtrarPrendas(temperatura);
		separarPrendas(prendas);
		if(puedeGenerarSugerencia()) {
		return Lists
				.cartesianProduct(prendasSuperiores,prendasInferiores,calzados,accesorios)
				.stream()
				.map((list)->new Atuendo(list.get(0),list.get(1), list.get(2), list.get(3)))
				.collect(Collectors.toList());	
		}else{
			throw new FaltanteDePrendasException("Debe tener al menos una prenda inferior, superior y calzado para generar sugerencia");
		}
	}
	// esto es para sugerir en una fecha
	public List<Atuendo> sugerir(LocalDate fecha,List<Prenda> prendas){
		
		Double temperatura=this.proveedorClima.getTemperaturaDeUnaFecha(fecha);
		//filtrarPrendas(temperatura);
		separarPrendas(prendas);
		if(puedeGenerarSugerencia()) {
		return Lists
				.cartesianProduct(prendasSuperiores,prendasInferiores,calzados,accesorios)
				.stream()
				.map((list)->new Atuendo(list.get(0),list.get(1), list.get(2), list.get(3)))
				.collect(Collectors.toList());	
		}else{
			throw new FaltanteDePrendasException("Debe tener al menos una prenda inferior, superior y calzado para generar sugerencia");
		}
	}
	
	public boolean puedeGenerarSugerencia() {
		return !(prendasSuperiores.isEmpty() || prendasInferiores.isEmpty() || calzados.isEmpty());
	}
}
