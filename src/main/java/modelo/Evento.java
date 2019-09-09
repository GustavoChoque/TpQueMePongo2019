package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.uqbar.commons.model.annotations.Observable;

import auxiliar.Frecuencia;
import modelo.operaciones.Operacion;
import repositorios.RepositorioSugerenciasPasadas;
import servicios.ProveedorOpenWeather;
@Observable
public class Evento {
	
	private LocalDate fecha;
	private String nombre;
	private Usuario usuario;
	private Guardaropa guardaropa;
	private List<Atuendo> sugerencias;
	private Frecuencia frecuencia;
	private List<Operacion> operaciones;
	private Atuendo sugerenciaElegida;
	
	public Evento(LocalDate fecha,String nom,Usuario usu,Guardaropa guardaropa,Frecuencia frec){
		this.fecha=fecha;
		this.nombre=nom;
		this.usuario=usu;
		this.guardaropa=guardaropa;
		this.frecuencia=frec;
		this.operaciones=new ArrayList<Operacion>();
	}
	
	
	public void sugerir(Sugeridor sugeridor){
		
		List<Atuendo> nuevasSugerencias=sugeridor.sugerir(this.guardaropa.getPrendasDisponibles());
		
		if(this.sugerencias==null){
			this.sugerencias=nuevasSugerencias;
			usuario.notificarNuevasSugerencias(this);
		}
		else {
			
			if(!sonIguales(this.sugerencias,nuevasSugerencias))
			{	
				
				//esto cambiarlo para que se guarde con el usuario, talvez un id
				//RepositorioSugerenciasPasadas.instance().agregarConjuntoSugerencias(this.sugerencias);
				
				this.sugerencias=nuevasSugerencias;
				
				//este ver si es necesario
				//usuario.haySugerenciasNuevas(this.sugerencias);
				
				usuario.notificarNuevasSugerencias(this);
			}
			
		}
		
		
		
		
		if(this.estaTerminado()){
		//actualiza la fecha, para un evento repetitivo
		this.fecha=this.fecha.plusDays(frecuencia.valor());
		this.usuario.deshabilitarNotificacionesViejasDeEvento(this);
		}
	}
	
	public boolean esProximo(LocalDate fecha){
		//La api de pronostico solo da pronostico de 5 dias
		int proximidad=5;
		return (this.fecha.compareTo(fecha)>=0 && ChronoUnit.DAYS.between(fecha, this.fecha)<=proximidad);
				
	}
	
	public boolean estaTerminado(){
		//? ver bien, si usar <=0 o <0
		return this.fecha.compareTo(LocalDate.now())<=0;
	}
	
	public boolean sonIguales(List<Atuendo> lista1,List<Atuendo> lista2){
		//CollectionUtils.retainAll(lista1, lista2) me devuelve
		//una lista de los elementos de la lista que estan contenidos en la lista2
		return lista1.size()==lista2.size() && lista1.size()==CollectionUtils.retainAll(lista1, lista2).size();
	}
	
	
	
	public String getNombre() {
		return nombre;
	}


	public LocalDate getFecha() {
		return fecha;
	}
	
	public void operarEnSugerencia(Operacion operacion){
		operacion.hacer(this);
		this.operaciones.add(operacion);
		
	}
	//aqui estoy repitiendo, ver como solucionar despues;
	public void aceptarSugerencia(int posicion){
		Atuendo atuendoAceptado=this.sugerencias.get(posicion);
		atuendoAceptado.setEstadoComoSugerencia(EstadoComoSugerencia.ACEPTADA);
		guardaropa.deshabilitarPrendas(atuendoAceptado.obtenerPrendas());
		//esto talvez pensalo mejor
		if(this.sugerenciaElegida==null){
			this.sugerenciaElegida=atuendoAceptado;
			}else{
				this.sugerenciaElegida.setEstadoComoSugerencia(EstadoComoSugerencia.RECHAZADA);
				this.sugerenciaElegida=atuendoAceptado;
				
			}
		
	}
	
	public void rechazarSugerencia(int posicion){
		this.sugerencias.get(posicion).setEstadoComoSugerencia(EstadoComoSugerencia.RECHAZADA);
	}
	
	public void deshacerUltimaAccion(){
		this.operaciones.get(this.operaciones.size()-1).dehacer(this);
	}

	
	public int posicionDeSugerencia(Atuendo sugerencia){
		
		return this.sugerencias.indexOf(sugerencia);
	}
	
	
	public List<Atuendo> getSugerencias() {
		return sugerencias;
	}


	public void limpiarDecisionSobreSugerencia(int posicionSugerencia) {
		this.sugerencias.get(posicionSugerencia).setEstadoComoSugerencia(EstadoComoSugerencia.NINGUNA);;
		
	}


	public Atuendo getSugerenciaElegida() {
		return sugerenciaElegida;
	}


	public void setSugerenciaElegida(Atuendo sugerenciaElegida) {
		this.sugerenciaElegida = sugerenciaElegida;
	}


	public List<Operacion> getOperaciones() {
		return operaciones;
	}


	public void setOperaciones(List<Operacion> operaciones) {
		this.operaciones = operaciones;
	}
	
	//La uso para un test nomas
	public void agregarSugerencia(Atuendo atuendo) {
		sugerencias = new ArrayList<Atuendo>();
		this.sugerencias.add(atuendo);
	}
}
