package modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import repositorios.RepositorioEventos;
import servicios.ProveedorOpenWeather;

public class Usuario {
	List<Guardaropa> guardaropas;
	List<Atuendo> sugerencias;
	public Usuario(){
		this.guardaropas=new ArrayList<Guardaropa>();
		this.sugerencias=new ArrayList<Atuendo>();
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
		guardaropas.add(guardaropa);
	}
	//------------------------
	public List<Atuendo> getTodosLosAtuendos(){
		List<Atuendo> todosLosAtuendos = new ArrayList<Atuendo>();
		Sugeridor su=new Sugeridor(new ProveedorOpenWeather());
		guardaropas.stream().filter(guardaropa->guardaropa.puedeGenerarSugerencia()).collect(Collectors.toList()).forEach(guardaropa->todosLosAtuendos.addAll(su.sugerir(guardaropa.getPrendas() )));
		
		
		return todosLosAtuendos;
		
	}
	
	public int cuantosAtuendosTengo() {
		return this.getTodosLosAtuendos().size();
	}
	
	public int cuantosGuardarropasTengo() {
		return guardaropas.size();
	}
}
