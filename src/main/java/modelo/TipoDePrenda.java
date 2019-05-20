package modelo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import exceptions.TelaInvalidaException;
import exceptions.TipoDePrendaInvalidoException;
import repositorios.RepositorioTiposDePrenda;

public class TipoDePrenda{
	
	Categoria categoria;
	String nombre;
	//List<Tela> telasPermitidas;
	
	public TipoDePrenda(){}
	
	public TipoDePrenda(Categoria cat,String nom){
		if(RepositorioTiposDePrenda.instance().tipoDePrendaValido(cat, nom)){		
		this.categoria=cat;
		this.nombre=nom; 
		}else{
			throw new TipoDePrendaInvalidoException("No es valida la categoria con el tipo");
			
		}
	}
	
	/*
	public TipoDePrenda(Categoria cat,String name, List<Tela> telasPermitida) {
		this.categoria = cat;
		this.nombre=name;
		this.telasPermitidas=telasPermitida;
	}
	
	public void validarTela(Tela telita,Prenda prenda) {
		Objects.requireNonNull(telita,"Debe especificar el tipo de tela");
		if(this.verificarTela(telita)) {
			prenda.setTela(telita);
		}else {
			throw new TelaInvalidaException("La tela no es valida para esta prenda");
		}
	}
	
	public Boolean verificarTela(Tela telita) {
		return this.telasPermitidas.contains(telita);
		
	}
	*/
	
	// Prendas conocidas:
	
	public Categoria getCategoria() {
		return categoria;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setCategoria(String categoria) {
		
		this.categoria = Categoria.valueOf(categoria);
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//los sobre escribi para poder usar el equals, para compara objetos de este tipo
		@Override
		public int hashCode() {
		return nombre.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
		TipoDePrenda tipo= (TipoDePrenda)obj;
		return tipo.getNombre().equals(this.getNombre()) && tipo.getCategoria().equals(this.getCategoria());
		}

}
