package modelo.observadores;

import modelo.Evento;
import modelo.Usuario;

public class AlertaNuevasSugerenciasObserver implements SugerenciasObserver{

	@Override
	public void notificarSugerenciasNuevas(Usuario usuario, Evento evento) {
		// TODO Auto-generated method stub
		System.out.println("Hola "+usuario+
				" estan las sugerencias para el evento "+evento.getNombre()+
				" y son estas: ");
	}

}
