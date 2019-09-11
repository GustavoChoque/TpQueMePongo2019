package modelo;

import java.util.List;

import javax.persistence.Entity;
@Entity
public class Premium extends TipoDeUsuario{
	public Premium() {}
	@Override
	public List<Prenda> agregarPrenda(List<Prenda> guardarropas, Prenda prenda) {
		guardarropas.add(prenda);
		return guardarropas;
	}

}
