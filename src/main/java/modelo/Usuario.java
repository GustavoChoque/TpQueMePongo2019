package modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import repositorios.RepositorioEventos;
import servicios.ProveedorOpenWeather;

public class Usuario {
	private List<Guardaropa> guardaropas;
	private List<Atuendo> sugerencias;
	private TipoDeUsuario tipoDeUsuario;
	public Usuario(TipoDeUsuario tipo){
		this.guardaropas=new ArrayList<Guardaropa>();
		this.sugerencias=new ArrayList<Atuendo>();
		this.tipoDeUsuario=tipo;
	}
	
	public List<Guardaropa> getGuardaropas() {
		return guardaropas;
	}
	
	public void haySugerenciasNuevas(List<Atuendo> nuevasSugerencias){
		this.sugerencias=nuevasSugerencias;
	}
	
	public void crearEvento(LocalDate fecha,String nombre,Guardaropa guardaropa){
		Evento nuevoEvento=new Evento(fecha, nombre, this, guardaropa);
		RepositorioEventos.instance().agendar(nuevoEvento);
	}
	
	
	public void agregarGuardaropa(Guardaropa guardaropa) {
		guardaropa.setUsuario(this);
		guardaropas.add(guardaropa);
	}
	
	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}
	
	//------------------------
	/*public List<Atuendo> getTodosLosAtuendos(){
		List<Atuendo> todosLosAtuendos = new ArrayList<Atuendo>();
		Sugeridor su=new Sugeridor(new ProveedorOpenWeather());
		guardaropas.stream().filter(guardaropa->guardaropa.puedeGenerarSugerencia()).collect(Collectors.toList()).forEach(guardaropa->todosLosAtuendos.addAll(su.sugerir(guardaropa.getPrendas() )));
		
		
		return todosLosAtuendos;
		
	}
	
	public int cuantosAtuendosTengo() {
		return this.getTodosLosAtuendos().size();
	}*/
	
	public int cuantosGuardarropasTengo() {
		return guardaropas.size();
	}
}
