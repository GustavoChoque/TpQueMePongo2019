package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prenda {

	public Tela tela;
	public TipoDePrenda tipo;
	public Color colorPrimario;
	public Color colorSecundario;
	
	
	public Prenda(TipoDePrenda tipo, Tela tel, Color primario) {
		this.tipo = Objects.requireNonNull(tipo,"Debe especificar la tipo");
		Objects.requireNonNull(tel, "Debe especificar la tela");
		if(!tipo.verificarTela(tel)) throw new RuntimeException("El tipo de tela no exite para esta prenda");
		this.tela= tel;
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
