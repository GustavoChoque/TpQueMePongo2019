package modelo;

import java.util.List;

public class Premium implements TipoDeUsuario{

	@Override
	public List<Prenda> agregarPrenda(List<Prenda> guardarropas, Prenda prenda) {
		guardarropas.add(prenda);
		return guardarropas;
	}

}
