package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import servicios.ProveedorOpenWeather;

public class Usuario {
	List<Guardaropa> guardaropas;
	
	public Usuario(){
		guardaropas=new ArrayList<Guardaropa>();
	}
	
	public List<Guardaropa> getGuardaropas() {
		return guardaropas;
	}
	
	public void agregarGuardaropa(Guardaropa guardaropa) {
		guardaropas.add(guardaropa);
	}
	
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
