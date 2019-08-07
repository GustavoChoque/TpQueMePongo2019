package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import exceptions.GuardarropaIncompletoException;


public class Guardaropa {
	
	private List<Prenda> prendas;
	private Usuario usuario;
	
	public Guardaropa() {
		prendas=new ArrayList<Prenda>();
		
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
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public List<Prenda> getPrendasDisponibles(){
		return this.prendas.stream().filter(p->p.isHabilitado()).collect(Collectors.toList());
	}
	public void agregarPrendaDirecto(Prenda prenda) {
		prendas.add(prenda);
	}
	
}	
