package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import servicios.ProveedorOpenWeather;

public class Evento {
	
	private LocalDate fecha;
	private String nombre;
	private Usuario usuario;
	private Guardaropa guardaropa;
	private List<Atuendo> sugerencias;

	
	public Evento(LocalDate fecha,String nom,Usuario usu,Guardaropa guardaropa){
		this.fecha=fecha;
		this.nombre=nom;
		this.usuario=usu;
		this.guardaropa=guardaropa;
	}
	
	
	
	public void sugerir(Sugeridor sugeridor){
		this.sugerencias=sugeridor.sugerir(this.guardaropa.getPrendas());
		usuario.haySugerenciasNuevas(this.sugerencias);
		
	}
	
	public boolean esProximo(LocalDate fecha){
		//La api de pronostico solo da pronostico de 5 dias
		int proximidad=5;
		return (this.fecha.compareTo(fecha)>=0 && Period.between(fecha, this.fecha).getDays()<=proximidad);
				
	}
}
