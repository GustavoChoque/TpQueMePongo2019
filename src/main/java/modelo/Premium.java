package modelo;

import java.util.List;

public class Premium implements TipoDeUsuario{

	@Override
	public List<Prenda> agregarPrenda(List<Prenda> listaPrendas, Prenda prenda) {
		listaPrendas.add(prenda);
		return listaPrendas;
	}

}
