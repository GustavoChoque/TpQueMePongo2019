package modelo;

import java.util.List;
import java.util.stream.Collectors;

public class GuardaropaCompartido extends Guardaropa {

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
	
}
