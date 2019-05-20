package servicios;

import java.time.LocalDate;

public class Weather {
	private double temperatura;
	private double viento;
	private double probLluvia;
	private LocalDate fecha;
	
	
	public Weather(double temp,double viento,double probll,LocalDate fecha){
		this.temperatura=temp;
		this.viento=viento;
		this.probLluvia=probll;
		this.fecha=fecha;
		
	}
	
	public Weather(double temp,LocalDate fecha){
		this.temperatura=temp;
		this.fecha=fecha;
		
		
	}
	
	
	public double getTemperatura() {
		return temperatura;
	}
	public double getViento() {
		return viento;
	}
	public double getProbLluvia() {
		return probLluvia;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public void setViento(double viento) {
		this.viento = viento;
	}

	public void setProbLluvia(double probLluvia) {
		this.probLluvia = probLluvia;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	
}
