package modelo.operaciones;

import modelo.Evento;

public class AceptarSugerencia implements Operacion{
	int posicionSugerencia;
	public AceptarSugerencia(int pos){
		this.posicionSugerencia=pos;
	};
	@Override
	public void hacer(Evento evento) {
		evento.aceptarSugerencia(posicionSugerencia);
	}

	@Override
	public void dehacer(Evento evento) {
		
		evento.limpiarDecisionSobreSugerencia(posicionSugerencia);
		//ver bien esta parte
		evento.setSugerenciaElegida(null);
	}

	

}
