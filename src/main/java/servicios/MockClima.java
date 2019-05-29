package servicios;

import java.time.LocalDate;

public class MockClima implements ProveedorClima{
	
	private double viento;
	private double temp;
	private double lluv;
	
	public MockClima(double v, double t, double l) {
		this.viento = v;
		this.temp = t;
		this.lluv = l;
	}
	
	
	@Override
	public double getTemperatura() {
				
		return this.temp;
	}

	@Override
	public double getViento() {
		
		return this.viento;
	}

	@Override
	public double getProbLluvia() {
		
		return this.lluv;
	}

	@Override
	public double getTemperaturaDeUnaFecha(LocalDate fecha) {

		return 00.00;
	}

	

}
