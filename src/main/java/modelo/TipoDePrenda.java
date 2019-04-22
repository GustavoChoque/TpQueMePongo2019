package modelo;

public class TipoDePrenda{
	
	Categoria categoria;
	
	public TipoDePrenda(Categoria cat) {
		this.categoria = cat;
	}
	
	
	// Prendas conocidas:
	
	static TipoDePrenda Zapato = new TipoDePrenda(Categoria.CALZADO);
	static TipoDePrenda Ojotas = new TipoDePrenda(Categoria.CALZADO);
	static TipoDePrenda Camisa = new TipoDePrenda(Categoria.PARTE_SUPERIOR);
	static TipoDePrenda Remera = new TipoDePrenda(Categoria.PARTE_SUPERIOR);
	static TipoDePrenda Pantalon = new TipoDePrenda(Categoria.PARTE_INFERIOR);
	
}
