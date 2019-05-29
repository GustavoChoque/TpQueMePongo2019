package modelo;

public class AtuendoSugerido {

	private Atuendo atuendo;
	private int eleccion;
	
	public AtuendoSugerido(Atuendo outfit, int election) {
		this.atuendo = outfit;
		this.eleccion = election;
	}
	
	// Aceptado si la eleccion es 1, caso contrario fue rechazado por el usuario
	public boolean estaAceptado() {
		return this.eleccion == 1;
	}
	
	public Atuendo getAtuendo() {
		return this.atuendo;
	}
	
	public int getEleccion() {
		return this.eleccion;
	}
}
