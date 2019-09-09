package modelo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import exceptions.TelaInvalidaException;
import exceptions.TipoDePrendaInvalidoException;
import repositorios.RepositorioTiposDePrenda;
@Entity
public class TipoDePrenda{
	
	@Id@GeneratedValue
	private int id;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	private String nombre;
	private int capa;
	private int nivelDeAbrigo;
	
	public TipoDePrenda(){}
	
	public TipoDePrenda(Categoria cat,String nom,int capa,int abrigo){
		if(RepositorioTiposDePrenda.instance().tipoDePrendaValido(cat, nom)){		
			this.categoria=cat;
			this.nombre=nom;
			this.capa=capa;
			this.nivelDeAbrigo=abrigo;
			}else{
				throw new TipoDePrendaInvalidoException("No es valida la categoria con el tipo");
				
			}
	}
	
	

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void setCategoria(String categoria) {
				
		this.categoria = Categoria.valueOf(categoria);
	}
	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getNombre() {
		return nombre;
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

	public int getCapa() {
		return capa;
	}

	public int getNivelDeAbrigo() {
		return nivelDeAbrigo;
	}

	public void setCapa(int capa) {
		this.capa = capa;
	}

	public void setNivelDeAbrigo(int nivelDeAbrigo) {
		this.nivelDeAbrigo = nivelDeAbrigo;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

}
