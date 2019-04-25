package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		guardaropas.stream().filter(guardaropa->guardaropa.puedeGenerarSugerencia()).collect(Collectors.toList()).forEach(guardaropa->todosLosAtuendos.addAll(guardaropa.generarSugerencia ()));
		return todosLosAtuendos;
		
	}
	
	public int cuantosAtuendosTengo() {
		return this.getTodosLosAtuendos().size();
	}
	
	public int cuantosGuardarropasTengo() {
		return guardaropas.size();
	}
}
