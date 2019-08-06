package modelo.operaciones;

import modelo.Evento;

public interface Operacion {
	public void hacer(Evento evento);
	public void dehacer(Evento evento);
}
