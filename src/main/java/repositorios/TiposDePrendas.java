package repositorios;

import java.util.Arrays;

import modelo.Categoria;
import modelo.Tela;
import modelo.TipoDePrenda;

public class TiposDePrendas {
	
	public static TipoDePrenda Zapato = new TipoDePrenda(Categoria.CALZADO,"zapato",Arrays.asList(Tela.ALGODON,Tela.CUERO)) ;
	public static TipoDePrenda Ojotas = new TipoDePrenda(Categoria.CALZADO,"ojota",Arrays.asList(Tela.CUERO));
	public static TipoDePrenda Camisa = new TipoDePrenda(Categoria.PARTE_SUPERIOR,"camisa",Arrays.asList(Tela.ALGODON,Tela.SEDA));
	public static TipoDePrenda Remera = new TipoDePrenda(Categoria.PARTE_SUPERIOR,"remera",Arrays.asList(Tela.ALGODON,Tela.SEDA));
	public static TipoDePrenda Pantalon = new TipoDePrenda(Categoria.PARTE_INFERIOR,"pantalon",Arrays.asList(Tela.ALGODON,Tela.SEDA));
	public static TipoDePrenda Short = new TipoDePrenda(Categoria.PARTE_INFERIOR,"short",Arrays.asList(Tela.ALGODON,Tela.SEDA));
	public static TipoDePrenda AnteojosDeSol = new TipoDePrenda(Categoria.ACCESORIO,"AnteojosDeSol",Arrays.asList(Tela.NINGUNO));
	public static TipoDePrenda SinAccesorios = new TipoDePrenda(Categoria.ACCESORIO, "Sin accesorios",Arrays.asList(Tela.NINGUNO));

}
