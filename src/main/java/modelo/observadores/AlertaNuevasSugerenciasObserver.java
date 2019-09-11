package modelo.observadores;

import javax.persistence.Entity;

import modelo.Evento;
import modelo.Usuario;
@Entity
public class AlertaNuevasSugerenciasObserver extends SugerenciasObserver{
	public AlertaNuevasSugerenciasObserver(){}
	@Override
	public void notificarSugerenciasNuevas(Usuario usuario, Evento evento) {
		// TODO Auto-generated method stub
		System.out.println("Se envio mail con:=> Hola "+usuario+
				" estan las sugerencias para el evento "+evento.getNombre()+
				" y son estas: "+evento.getSugerencias());
	}

}
