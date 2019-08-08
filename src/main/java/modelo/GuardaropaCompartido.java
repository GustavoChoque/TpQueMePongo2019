package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class GuardaropaCompartido extends Guardaropa {
	
	private Set<Usuario> usuarios;
	
	
	public GuardaropaCompartido() {
		super();
		usuarios = new HashSet<Usuario>();
		usuarios.add(this.getUsuario());
		
	}
	
	

	@Override
	public List<Prenda> getPrendas() {
		// TODO Auto-generated method stub
		
		return super.getPrendas()
				.stream().filter(p->p.isHabilitado())
				.collect(Collectors.toList());
	}
	 public void bloquearPrenda(Prenda prenda){
		 super.getPrendas()
		 .stream().filter(p->p.equals(prenda))
		 .findAny()
		 .get()
		 .setHabilitado(false);
		
	 }
	
	public void notificarSugerenciaAceptada(Atuendo atuendo){
		atuendo.obtenerPrendas().forEach(p->this.bloquearPrenda(p));
	}
	@Override
	public List<Prenda> getPrendasDisponibles(){
		return this.getPrendas().stream().filter(p->p.isHabilitado()).collect(Collectors.toList());
	}
	@Override
	public void agregarPrenda(Prenda prenda) {
		prendas.add(prenda);
	}
	
	@Override
	public void deshabilitarPrendas(List<Prenda> unasPrendas) {
		unasPrendas.forEach(p->p.setHabilitado(false));
	}
	
}
