package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import exceptions.GuardarropaIncompletoException;
import repositorios.TiposDePrendas;

public class Guardaropa {
	
	List<Prenda> prendas;
	//listas auxiliares para el metodo puedegenerarSugerencias()  
	List<Prenda> prendasSuperiores;
	List<Prenda> prendasInferiores;
	List<Prenda> calzados;
	List<Prenda> accesorios;
	
	public Guardaropa() {
		prendas=new ArrayList<Prenda>();
	}
	public List<Prenda> getPrendas() {
		return prendas;
	}
	
	private void separarPrendas(List<Prenda> prendas){
		prendasSuperiores=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_SUPERIOR)).collect(Collectors.toList());
		prendasInferiores=prendas.stream().filter(p->p.getCategoria().equals(Categoria.PARTE_INFERIOR)).collect(Collectors.toList());
		calzados=prendas.stream().filter(p->p.getCategoria().equals(Categoria.CALZADO)).collect(Collectors.toList());
		accesorios=prendas.stream().filter(p->p.getCategoria().equals(Categoria.ACCESORIO)).collect(Collectors.toList());
		accesorios.add(new Prenda(new TipoDePrenda(Categoria.ACCESORIO, "SinAccesorio"),Color.NINGUNO,Tela.NINGUNO));

	}
	
	public boolean puedeGenerarSugerencia() {
		separarPrendas(this.prendas);
		return !(prendasSuperiores.isEmpty() || prendasInferiores.isEmpty() || calzados.isEmpty());
	}
	
	
	/*List<Prenda> prendasSuperiores;
	List<Prenda> prendasInferiores;
	List<Prenda> calzados;
	List<Prenda> accesorios;
	
	
	public Guardaropa() {
		prendasSuperiores = new ArrayList<Prenda>();
		prendasInferiores = new ArrayList<Prenda>();
		calzados = new ArrayList<Prenda>();
		accesorios = new ArrayList<Prenda>();
		accesorios.add(new Prenda(new TipoDePrenda(Categoria.ACCESORIO, "SinAccesorio"), Color.NINGUNO, Tela.NINGUNO));
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
	
	public void agregarAccesorio(Prenda acc) {
		this.accesorios.add(acc);
	}
	
	public  List<Atuendo>  generarSugerencia() {
		if(!this.puedeGenerarSugerencia()) {
			throw new GuardarropaIncompletoException("Debe tener al menos una prenda inferior, superior y calzado para generar sugerencia");
		}
		return Lists.cartesianProduct(prendasSuperiores,prendasInferiores,calzados,accesorios).stream().map((atuendo) -> new Atuendo(atuendo.get(0),atuendo.get(1),atuendo.get(2),atuendo.get(3))).collect(Collectors.toList());
	}
	
	public boolean puedeGenerarSugerencia() {
		return !(prendasSuperiores.isEmpty() || prendasInferiores.isEmpty() || calzados.isEmpty());
	}*/
}	
