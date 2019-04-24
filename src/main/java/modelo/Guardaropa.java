package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class Guardaropa {
	List<Prenda> prendasSuperiores;
	List<Prenda> prendasInferiores;
	List<Prenda> calzados;
	List<Prenda> accesorios;
	
	
	public Guardaropa() {
		prendasSuperiores = new ArrayList<Prenda>();
		prendasInferiores = new ArrayList<Prenda>();
		calzados = new ArrayList<Prenda>();
		accesorios = new ArrayList<Prenda>();
		accesorios.add(new Prenda(TipoDePrenda.SinAccesorios, Tela.NINGUNO, Color.NINGUNO));
	}
	
	public void agregarPrendaSuperior(Prenda p) {
		this.prendasSuperiores.add(p);
	}
	
	public void agregarPrendaInferior(Prenda p) {
		this.prendasInferiores.add(p);
	}
	
	public void agregarCalzado(Prenda p) {
		this.calzados.add(p);
	}
	
	public  List<Atuendo>  generarSugerencia() {
		if(prendasSuperiores.isEmpty() || prendasInferiores.isEmpty() || calzados.isEmpty()) {
			throw new NullPointerException("Debe tener al menos una prenda inferior, superior y calzado para generar sugerencia");
		}
		return Lists.cartesianProduct(prendasSuperiores,prendasInferiores,calzados,accesorios).stream().map((atuendo) -> new Atuendo(atuendo.get(0),atuendo.get(1),atuendo.get(2),atuendo.get(3))).collect(Collectors.toList());
	}
}	
