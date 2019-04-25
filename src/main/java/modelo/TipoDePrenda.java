package modelo;

import java.util.Arrays;
import java.util.List;

public class TipoDePrenda{
	
	Categoria categoria;
	String nombre;
	List<Tela> telasPermitidas;
	
	
	public TipoDePrenda(Categoria cat,String nombre, List<Tela> telasPermitidas) {
		this.categoria = cat;
		this.nombre=nombre;
		this.telasPermitidas=telasPermitidas;
	}
	public Boolean verificarTela(Tela telita) {
		return this.telasPermitidas.contains(telita);
		}

	
	// Prendas conocidas:
	
	public static TipoDePrenda Zapato = new TipoDePrenda(Categoria.CALZADO,"zapato",Arrays.asList(Tela.ALGODON,Tela.CUERO)) ;
	public static TipoDePrenda Ojotas = new TipoDePrenda(Categoria.CALZADO,"ojota",Arrays.asList(Tela.CUERO));
	public static TipoDePrenda Camisa = new TipoDePrenda(Categoria.PARTE_SUPERIOR,"camisa",Arrays.asList(Tela.ALGODON,Tela.SEDA));
	public static TipoDePrenda Remera = new TipoDePrenda(Categoria.PARTE_SUPERIOR,"remera",Arrays.asList(Tela.ALGODON,Tela.SEDA));
	public static TipoDePrenda Pantalon = new TipoDePrenda(Categoria.PARTE_INFERIOR,"pantalon",Arrays.asList(Tela.ALGODON,Tela.SEDA));
	public static TipoDePrenda Short = new TipoDePrenda(Categoria.PARTE_INFERIOR,"short",Arrays.asList(Tela.ALGODON,Tela.SEDA));
	public static TipoDePrenda AnteojosDeSol = new TipoDePrenda(Categoria.ACCESORIO,"AnteojosDeSol",Arrays.asList(Tela.NINGUNO));
	public static TipoDePrenda SinAccesorios = new TipoDePrenda(Categoria.ACCESORIO, "Sin accesorios",Arrays.asList(Tela.NINGUNO));
	
	public Categoria getCategoria() {
		return categoria;
	}

	public String getNombre() {
		return nombre;
	}
	
}
