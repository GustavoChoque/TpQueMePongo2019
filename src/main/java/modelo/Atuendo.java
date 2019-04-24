package modelo;

public class Atuendo {

	Prenda prendaSuperior;
	Prenda prendaInferior;
	Prenda calzado;
	Prenda accesorio;
	
	public Atuendo(Prenda sup, Prenda inf, Prenda cal, Prenda acc) {
		this.prendaSuperior = sup;
		this.prendaInferior = inf;
		this.calzado = cal;
		this.accesorio = acc;
	}
	
	public Prenda getAccesorio() {
		return this.accesorio;
	}
	
	public Prenda getSuperior() {
		return prendaSuperior;
	}

	public Prenda getInferior() {
		return prendaInferior;
	}

	public Prenda getCalzado() {
		return calzado;
	}
}
