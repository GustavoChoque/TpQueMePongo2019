package modelo;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import auxiliar.LocalDateConverter;

@Entity
public class Notificacion {
	
	@Id@GeneratedValue
	private int id;
	@Convert(converter=LocalDateConverter.class)
	private LocalDate fecha;
	@ManyToOne
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
	public int getId() {
		return id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	
	
	
}
