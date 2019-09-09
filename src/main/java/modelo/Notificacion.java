package modelo;

import java.time.LocalDate;


public class Notificacion {
	
	
	
	private LocalDate fecha;
	
	private Evento evento;
	private boolean habilitada;
	
	public Notificacion(){}
	public Notificacion(Evento evento){
		this.fecha=LocalDate.now();
		this.evento=evento;
		this.habilitada=true;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public String toString() {
		return "Notificacion[fechaSugerencia:" + fecha +" evento:"+this.evento.getNombre()+"]";
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
	
	
	
}
