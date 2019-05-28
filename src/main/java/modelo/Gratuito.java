package modelo;

import java.util.List;

import exceptions.LimiteListaException;

public class Gratuito implements TipoDeUsuario {
	private int limite=10;
	@Override
	public List<Prenda> agregarPrenda(List<Prenda> listaPrendas, Prenda prenda) {
		if(listaPrendas.size()<limite){
			listaPrendas.add(prenda);
		return listaPrendas;
		}else{
			throw new LimiteListaException("No hay mas espacio para la prenda");
		}
	}

}
