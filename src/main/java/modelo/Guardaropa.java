package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import exceptions.GuardarropaIncompletoException;


public class Guardaropa {
	
	protected List<Prenda> prendas;
	protected Usuario usuario;
	
	public Guardaropa() {
		prendas=new ArrayList<Prenda>();
		
	}
	public Guardaropa(List<Prenda> prendas, Usuario usuario) {
		this.prendas= prendas;
		this.usuario = usuario;
	}
	public List<Prenda> getPrendas() {
		return this.prendas;
	}
	
	public void agregarPrenda(Prenda prenda){
		//pido por teclado? si quiere agregar imagen a prenda
		/*
		 
		String path;
		prenda.cargarImagen(path);*/
		
		//this.prendas.add(prenda);
		List<Prenda> aux=this.usuario.getTipoDeUsuario().agregarPrenda(this.prendas, prenda);
		this.prendas=aux;
			
		
	}
	public void agregarPrendas(List<Prenda> prendas) {
		prendas.forEach(p->this.agregarPrenda(p));
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public List<Prenda> getPrendasDisponibles(){
		return this.prendas;
	}
	
	public void deshabilitarPrendas(List<Prenda> unasPrendas) {
		
	}
	
}	
