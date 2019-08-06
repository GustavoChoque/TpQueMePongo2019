package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.annotations.Observable;

import auxiliar.Frecuencia;
import modelo.Guardaropa;
import modelo.Usuario;

@Observable
public class EventoViewModel {
	
	private Usuario usuario;
	private String nombre;
	private String fecha;
	private List<Guardaropa> guardaropasPersonales;
	private Guardaropa guardaropa;
	private List<Frecuencia> frecuencias;
	private Frecuencia frecuencia;
	
	public EventoViewModel(Usuario usuario) {
		this.usuario=usuario;
		this.guardaropasPersonales=this.usuario.getGuardaropas();
		this.frecuencias=Arrays.asList(Frecuencia.values());
	}
	
	public void crearEvento(){
		String pattern = "dd/MM/yyyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate fechaformateada=LocalDate.parse(fecha, formatter);
		this.usuario.crearEvento(fechaformateada, nombre, guardaropa, frecuencia);
		System.out.println("Se creo Evento");
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
	

	public Guardaropa getGuardaropa() {
		return guardaropa;
	}

	public void setGuardaropa(Guardaropa guardaropa) {
		this.guardaropa = guardaropa;
	}

	public List<Guardaropa> getGuardaropasPersonales() {
		return guardaropasPersonales;
	}

	public void setGuardaropasPersonales(List<Guardaropa> guardaropasPersonales) {
		this.guardaropasPersonales = guardaropasPersonales;
	}

	public List<Frecuencia> getFrecuencias() {
		return frecuencias;
	}

	public void setFrecuencias(List<Frecuencia> frecuencias) {
		this.frecuencias = frecuencias;
	}

	public Frecuencia getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Frecuencia frecuencia) {
		this.frecuencia = frecuencia;
	}

}
