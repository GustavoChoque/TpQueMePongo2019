package modelo;

public class TipoDePrenda{
	
	Categoria categoria;
	String nombre;
	
	public TipoDePrenda(Categoria cat,String nombre) {
		this.categoria = cat;
		this.nombre=nombre;
	}
	
	
	// Prendas conocidas:
	
	public static TipoDePrenda Zapato = new TipoDePrenda(Categoria.CALZADO,"zapato");
	public static TipoDePrenda Ojotas = new TipoDePrenda(Categoria.CALZADO,"ojota");
	public static TipoDePrenda Camisa = new TipoDePrenda(Categoria.PARTE_SUPERIOR,"camisa");
	public static TipoDePrenda Remera = new TipoDePrenda(Categoria.PARTE_SUPERIOR,"remera");
	public static TipoDePrenda Pantalon = new TipoDePrenda(Categoria.PARTE_INFERIOR,"pantalon");
	public static TipoDePrenda Short = new TipoDePrenda(Categoria.PARTE_INFERIOR,"short");
	public static TipoDePrenda AnteojosDeSol = new TipoDePrenda(Categoria.ACCESORIO,"AnteojosDeSol");
	public static TipoDePrenda SinAccesorios = new TipoDePrenda(Categoria.ACCESORIO, "Sin accesorios");
	
	public Categoria getCategoria() {
		return categoria;
	}

	public String getNombre() {
		return nombre;
	}
	
}
