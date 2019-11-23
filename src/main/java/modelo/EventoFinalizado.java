package modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import auxiliar.LocalDateConverter;

@Entity
public class EventoFinalizado {
	@Id
	@GeneratedValue
	private int id;
	@Convert(converter=LocalDateConverter.class)
	private LocalDate fecha;
	private String nombre;
	@ManyToOne
	private Usuario usuario;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Atuendo sugerenciaElegida;
	private boolean calificado;
	@Enumerated(EnumType.STRING)
	private FeedbackUsuario calificacion;
	
	public EventoFinalizado(){}
	
	public EventoFinalizado(LocalDate fecha,String nombre,Usuario usu,Atuendo atuendo){
		this.fecha=fecha;
		this.nombre=nombre;
		this.usuario=usu;
		this.sugerenciaElegida=atuendo;
		this.calificado=false;
	}
	
	public int getId() {
		return id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public String getNombre() {
		return nombre;
	}
	
	public boolean isCalificado() {
		return calificado;
	}
	public FeedbackUsuario getCalificacion() {
		return calificacion;
	}

	public Atuendo getSugerenciaElegida() {
		return sugerenciaElegida;
	}
	public void calificar(FeedbackUsuario calificacion){
		setCalificacion(calificacion);
		setCalificado(true);
		this.usuario.darFeedback(calificacion);
		
	}

	public void setCalificado(boolean calificado) {
		this.calificado = calificado;
	}

	public void setCalificacion(FeedbackUsuario calificacion) {
		this.calificacion = calificacion;
	}
}
