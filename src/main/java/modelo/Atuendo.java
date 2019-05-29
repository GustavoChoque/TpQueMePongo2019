package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Atuendo {

	List<Prenda>prendasSuperiores;
	Prenda prendaInferior;
	Prenda calzado;
	Prenda accesorio;
	
	public Atuendo(List<Prenda> sup, Prenda inf, Prenda cal, Prenda acc) {
		this.prendasSuperiores = sup;
		this.prendaInferior = inf;
		this.calzado = cal;
		this.accesorio = acc;
	}
	
	public Prenda getAccesorio() {
		return this.accesorio;
	}
	
	public List<Prenda> getSuperiores(){
		return prendasSuperiores;
	}


	public Prenda getSuperiorCapaX(int capa) {
		return prendasSuperiores.stream().filter(p->p.getCapa()==capa).collect(Collectors.toList()).get(0);
	}

	public Prenda getInferior() {
		return prendaInferior;
	}

	public Prenda getCalzado() {
		return calzado;
	}
	public int getNivelDeAbrigo(){
		
		return this.prendasSuperiores.stream()
				.mapToInt(Prenda::getNivelDeAbrigo).sum()
				+this.prendaInferior.getNivelDeAbrigo()
				+this.calzado.getNivelDeAbrigo()
				+this.accesorio.getNivelDeAbrigo();
	}
}
