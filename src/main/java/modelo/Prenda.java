package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptions.TelaInvalidaException;

public class Prenda {

	public Tela tela;
	public TipoDePrenda tipo;
	public Color colorPrimario;
	public Color colorSecundario;
	
	
	public Prenda(TipoDePrenda tipo, Tela tel, Color primario) {
		this.tipo = Objects.requireNonNull(tipo,"Debe especificar el tipo de prenda");
		Objects.requireNonNull(tel, "Debe especificar la tela");
		this.colorPrimario = Objects.requireNonNull(primario, "El color primario es obligatorio");
		if(!tipo.verificarTela(tel)) ;
		this.tela= tel;
	}
	public void setColorSecundario(Color colour) {
		if(!this.colorPrimario.equals(colour)){
		this.colorSecundario = colour;
		}else{
			throw new RuntimeException("El color Secundario es igual al Color Primario ");
		}
	}
	
	public Categoria getCategoria(){
		
		return this.tipo.getCategoria();
	}
	public String getTipoDePrenda(){
		
		return this.tipo.getNombre();
	}

		
	
}
