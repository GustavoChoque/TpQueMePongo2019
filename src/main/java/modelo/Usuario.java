package modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.model.annotations.Observable;

import auxiliar.Frecuencia;
import modelo.observadores.SugerenciasObserver;
import repositorios.RepositorioEventos;
import servicios.ProveedorOpenWeather;
@Observable
public class Usuario {
	
	private List<Guardaropa> guardaropas;
	
	private TipoDeUsuario tipoDeUsuario;
	private List<Notificacion> notificaciones;
	private List<SugerenciasObserver> interesados;
	
	
	public Usuario(TipoDeUsuario tipo){
		this.guardaropas=new ArrayList<Guardaropa>();
		
		this.notificaciones=new ArrayList<Notificacion>();
		this.interesados=new ArrayList<SugerenciasObserver>();
		
		this.tipoDeUsuario=tipo;
	}
	
	public List<Guardaropa> getGuardaropas() {
		return guardaropas;
	}
	
	//ver si es necesario que Evento lo llame, por ahora no 
	/*public void haySugerenciasNuevas(List<Atuendo> nuevasSugerencias){
		this.sugerencias=nuevasSugerencias;
	}*/
	
	public void notificarNuevasSugerencias(Evento unEvento){
		//ver para q se pueda actualizar la lista al recibir una nueva
		deshabilitarNotificacionesViejasDeEvento(unEvento);
		//por ahora borrara las notificaciones viejas, ver luego como talvez bloquearlas o cambiar al gun color de fondo
		borrarNotificacionesDeshabilitadas();
		this.notificaciones.add(new Notificacion(unEvento));
		interesados.forEach(in->in.notificarSugerenciasNuevas(this, unEvento));
		
	}
	
	public void crearEvento(LocalDate fecha,String nombre,Guardaropa guardaropa, Frecuencia frecuencia){
		Evento nuevoEvento=new Evento(fecha, nombre, this, guardaropa,frecuencia);
		RepositorioEventos.instance().agendar(nuevoEvento);
	}
	
	public void agregarGuardaropa(Guardaropa guardaropa){
		guardaropa.setUsuario(this);
		this.guardaropas.add(guardaropa);
	}

	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}
	
	public void agregarInterezado(SugerenciasObserver o){
		this.interesados.add(o);
	}
	/*
	public void agregarConjuntoSugerenciasViejas(List<Atuendo> atuendos){
		this.listaSugerenciasViejas.addAll(atuendos);
	}
	*/

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public void setGuardaropas(List<Guardaropa> guardaropas) {
		this.guardaropas = guardaropas;
	}
	
	public void deshabilitarNotificacionesViejasDeEvento(Evento evento){
		this.notificaciones
		.forEach(n->{
			if(n.getEvento().getNombre()==evento.getNombre()){
				n.setHabilitada(false);
			}
		});
	}
	private void borrarNotificacionesDeshabilitadas(){

		this.notificaciones.removeIf(n->!n.isHabilitada());
	}

	
	/*
	public void deshacerUltimaOperacion() {
		Atuendo aux = historialSugerencias.get(0).getAtuendo();
		sugerencias.add(aux);		
		historialSugerencias.remove(0);
	}
	
	public void tomarSugerencia() {
		// Por defecto tomo la primera
		Atuendo sugerenciaActual = this.sugerencias.get(0);
		// AL tomarla, la elimino de sugerencias actuales 
		
		// Si acepta, lo agrego con un 1, caso contrario con un dos
		if(this.aceptaSugerencia(sugerenciaActual)) {
			this.sugerenciaAceptada(sugerenciaActual);
		} else {
			this.sugerenciaRechazada(sugerenciaActual);
		}
	}
	
	public List<AtuendoSugerido> mostrarHistorialSegunEleccion(int eleccion){
		return this.historialSugerencias.stream().filter(atuendo -> atuendo.getEleccion() == eleccion).collect(Collectors.toList());
	}
	
	
	// Mostrar el atuendo y darle la opcion al usuario de que lo acepte
	public boolean aceptaSugerencia(Atuendo sugerencia) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Su atuendo esta compuesto por: " + sugerencia.calzado + ", " + sugerencia.prendaInferior + ", " + sugerencia.accesorio );
		System.out.println("Si lo desea aceptar, ingrese 1, caso contrario, ingrese cualquier numero");
		int eleccion = sc.nextInt();
		return eleccion == 1;
	}
	
	public void sugerenciaAceptada(Atuendo sugerencia) {
		this.sugerencias.remove(0);
		AtuendoSugerido aceptado = new AtuendoSugerido(sugerencia,1);
		historialSugerencias.add(aceptado);
	}

	public void sugerenciaRechazada(Atuendo sugerencia) {
		this.sugerencias.remove(0);
		AtuendoSugerido rechazado = new AtuendoSugerido(sugerencia,2);
		historialSugerencias.add(rechazado);
	}
	//------------------------

	
	public int cuantosGuardarropasTengo() {
		return guardaropas.size();
	}
	
	public List<Atuendo> getSugerencias(){
		return sugerencias;
	}
	public List<AtuendoSugerido> getHistorialSugerencias(){
		return historialSugerencias;
	}*/
}
