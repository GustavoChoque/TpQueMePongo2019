package modelo;

import java.util.Objects;

public class Prenda {

	public Tela tela;
	public TipoDePrenda tipo;
	public Color colorPrimario;
	public Color colorSecundario;
	
	public Prenda(Categoria cat, Tela tel, Color primario) {
		this.tipo = new TipoDePrenda(cat);
		this.tela = Objects.requireNonNull(tel, "Debe especificar la tela");
		this.colorPrimario = Objects.requireNonNull(primario, "El color es obligatorio");
	}
	
	public void setColorSecundario(Color colour) {
		this.colorSecundario = colour;
	}

	
}
