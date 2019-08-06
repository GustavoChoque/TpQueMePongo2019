package modelo.operaciones;

import modelo.Evento;

public class RechazarSugerencia implements Operacion{
	
	int posicionSugerencia;
	public RechazarSugerencia(int pos){
		this.posicionSugerencia=pos;
	};
	
	@Override
	public void hacer(Evento evento) {
		evento.rechazarSugerencia(posicionSugerencia);
		
	}

	@Override
	public void dehacer(Evento evento) {
		evento.limpiarDecisionSobreSugerencia(posicionSugerencia);
		
	}

}
