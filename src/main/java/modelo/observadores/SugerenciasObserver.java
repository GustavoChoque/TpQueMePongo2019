package modelo.observadores;

import modelo.Evento;
import modelo.Usuario;

public interface SugerenciasObserver {
	public void notificarSugerenciasNuevas(Usuario usuario,Evento evento);
}
