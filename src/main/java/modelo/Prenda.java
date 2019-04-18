package modelo;

public class Prenda {

	public Categoria categoria;
	public Tela tela;
	public Tipo tipo;
	public Color colorPrimario;
	public Color colorSecundario;
	
	
	public Prenda(Categoria cat,Tipo tip, Tela tel, Color primario) {
		this.categoria = cat;
		this.tela = tel;
		this.tipo = tip;
		this.colorPrimario = primario;
	}
	
	public void setColorSecundario(Color colour) {
		this.colorSecundario = colour;
	}

	
}
