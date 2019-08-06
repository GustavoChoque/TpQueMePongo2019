package ui;


import java.util.List;

import org.uqbar.commons.model.annotations.Observable;

import modelo.Atuendo;
import modelo.Evento;
import modelo.operaciones.RechazarSugerencia;
import modelo.operaciones.AceptarSugerencia;

@Observable
public class SugerenciasViewModel {
	private Evento evento;

	private List<Atuendo> sugerencias;
	private Atuendo sugerenciaSeleccionada;
	public SugerenciasViewModel(Evento eventoElegido) {
		this.evento=eventoElegido;
		
		this.sugerencias=this.evento.getSugerencias();
	}
	
	public void AceptarSugerencia(){
		evento.operarEnSugerencia(new AceptarSugerencia(evento.posicionDeSugerencia(this.sugerenciaSeleccionada)));
	}
	public void RechazarSugerencia(){
		evento.operarEnSugerencia(new RechazarSugerencia(evento.posicionDeSugerencia(this.sugerenciaSeleccionada)));
	}
	
	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public List<Atuendo> getSugerencias() {
		return sugerencias;
	}
	public void setSugerencias(List<Atuendo> sugerencias) {
		this.sugerencias = sugerencias;
	}
	public Atuendo getSugerenciaSeleccionada() {
		return sugerenciaSeleccionada;
	}
	public void setSugerenciaSeleccionada(Atuendo sugerenciaSeleccionada) {
		this.sugerenciaSeleccionada = sugerenciaSeleccionada;
	}
	
}
