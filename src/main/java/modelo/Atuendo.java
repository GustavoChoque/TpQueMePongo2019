package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Atuendo {

	Prenda prendaSuperior;
	Prenda prendaSuperior2;
	Prenda prendaSuperior3;
	Prenda prendaInferior;
	Prenda calzado;
	Prenda accesorio;
	EstadoComoSugerencia estadoComoSugerencia;
	
	public Atuendo(Prenda sup, Prenda sup2,Prenda sup3,Prenda inf, Prenda cal, Prenda acc) {
		this.prendaSuperior = sup;
		this.prendaSuperior2=sup2;
		this.prendaSuperior3=sup3;
		this.prendaInferior = inf;
		this.calzado = cal;
		this.accesorio = acc;
		this.estadoComoSugerencia=EstadoComoSugerencia.NINGUNA;
	}
	public Prenda getPrendaSuperior() {
		return prendaSuperior;
	}

	public Prenda getPrendaInferior() {
		return prendaInferior;
	}

	public Prenda getCalzado() {
		return calzado;
	}

	public Prenda getAccesorio() {
		return accesorio;
	}

	public Prenda getPrendaSuperior2() {
		return prendaSuperior2;
	}

	public Prenda getPrendaSuperior3() {
		return prendaSuperior3;
	}
	public int getNivelDeAbrigo(){
		return this.prendaSuperior.getNivelDeAbrigo()
				+this.prendaSuperior2.getNivelDeAbrigo()
				+this.prendaSuperior3.getNivelDeAbrigo()
				+this.prendaInferior.getNivelDeAbrigo()
				+this.calzado.getNivelDeAbrigo()
				+this.accesorio.getNivelDeAbrigo();
	}
	public List<Prenda> obtenerPrendas(){
		List<Prenda> listaAux=new ArrayList<Prenda>();
		listaAux.add(this.prendaSuperior);
		listaAux.add(this.prendaSuperior2);
		listaAux.add(this.prendaSuperior3);
		listaAux.add(this.prendaInferior);
		listaAux.add(this.calzado);
		listaAux.add(this.accesorio);
		return listaAux;
	}

	public void setEstadoComoSugerencia(EstadoComoSugerencia estadoComoSugerencia) {
		this.estadoComoSugerencia = estadoComoSugerencia;
	}

	public EstadoComoSugerencia getEstadoComoSugerencia() {
		return estadoComoSugerencia;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
				return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		Atuendo atuendo = (Atuendo) obj;
		return atuendo.getPrendaSuperior().equals(this.prendaSuperior)
				&& atuendo.getPrendaSuperior2().equals(this.prendaSuperior2)
				&& atuendo.getPrendaSuperior3().equals(this.prendaSuperior3)
				&& atuendo.getPrendaInferior().equals(this.prendaInferior)
				&& atuendo.getCalzado().equals(this.calzado)
				&& atuendo.getAccesorio().equals(this.accesorio);
	}

	@Override
	public String toString() {
		return "Atuendo [ estado=" + estadoComoSugerencia + ", SuperiorCapa1=" + prendaSuperior + ", SuperiorCapa2=" + prendaSuperior2
				+ ", SuperiorCapa3=" + prendaSuperior3 + ", Inferior=" + prendaInferior + ", calzado=" + calzado
				+ ", accesorio=" + accesorio + ", estado=" + estadoComoSugerencia + "]";
	}
	


	public void deshabilitarPrendas() {
		prendaSuperior.setHabilitado(false);
		prendaSuperior2.setHabilitado(false);
		prendaSuperior3.setHabilitado(false);
		prendaInferior.setHabilitado(false);
		calzado.setHabilitado(false);
		accesorio.setHabilitado(false);
	}
		
	}
