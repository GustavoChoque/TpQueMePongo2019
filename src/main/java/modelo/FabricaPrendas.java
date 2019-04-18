package modelo;

public class FabricaPrendas {
	
	
	
	public Prenda crearRemera(Tela tela, Color color) {
		Prenda remera = new Prenda(Categoria.PARTE_SUPERIOR,Tipo.REMERA_MANGA_CORTA,tela,color);
		return remera;
	}

}
