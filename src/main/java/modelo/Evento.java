package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import auxiliar.Frecuencia;
import servicios.ProveedorOpenWeather;

public class Evento {
	
	private LocalDate fecha;
	private String nombre;
	private Usuario usuario;
	private Guardaropa guardaropa;
	private List<Atuendo> sugerencias;
	private Frecuencia frecuencia;
	
	public Evento(LocalDate fecha,String nom,Usuario usu,Guardaropa guardaropa,Frecuencia frec){
		this.fecha=fecha;
		this.nombre=nom;
		this.usuario=usu;
		this.guardaropa=guardaropa;
		this.frecuencia=frec;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void sugerir(Sugeridor sugeridor){
		this.sugerencias=sugeridor.sugerir(this.guardaropa.getPrendas());
		usuario.haySugerenciasNuevas(this.sugerencias);
		usuario.notificarNuevasSugerencias(this);
		//actualiza la fecha, para un evento repetitivo
		this.fecha=this.fecha.plusDays(frecuencia.valor());
	}
	
	public boolean esProximo(LocalDate fecha){
		//La api de pronostico solo da pronostico de 5 dias
		int proximidad=5;
		return (this.fecha.compareTo(fecha)>=0 && ChronoUnit.DAYS.between(fecha, this.fecha)<=proximidad);
				
	}

	public Frecuencia getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Frecuencia frecuencia) {
		this.frecuencia = frecuencia;
	}

	public LocalDate getFecha() {
		return fecha;
	}
}
