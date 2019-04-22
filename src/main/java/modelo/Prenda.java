package modelo;

import java.util.Objects;

public class Prenda {

	public Tela tela;
	public TipoDePrenda tipo;
	public Color colorPrimario;
	public Color colorSecundario;
	
	public Prenda(TipoDePrenda tipo, Tela tel, Color primario) {
		this.tipo = Objects.requireNonNull(tipo,"Debe especificar la tipo");
		this.tela = Objects.requireNonNull(tel, "Debe especificar la tela");
		this.colorPrimario = Objects.requireNonNull(primario, "El color es obligatorio");
	}
	
	public void setColorSecundario(Color colour) {
		if(!this.colorPrimario.equals(colour)){
		this.colorSecundario = colour;
		}else{
			throw new RuntimeException("El color Secundario es igual al Color Primario ");
		}
	}
	
	public Categoria getCategortia(){
		
		return this.tipo.getCategoria();
	}
	public String getTipoDePrenda(){
		
		return this.tipo.getNombre();
	}
	
}
