package modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import exceptions.LimiteListaException;
@Entity
public class Gratuito extends TipoDeUsuario {
	@Column(name="limite")
	private int limite=10;
	public Gratuito() {}
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
