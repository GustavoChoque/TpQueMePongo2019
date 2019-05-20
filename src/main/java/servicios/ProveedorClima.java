package servicios;

import java.time.LocalDate;

public interface ProveedorClima {
	public double getTemperatura();
	public double getViento();
	public double getProbLluvia();
	public double getTemperaturaDeUnaFecha(LocalDate fecha);
}
