package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import sevicios.ProveedorClima;
import sevicios.ProveedorOpenWeather;

public class Main {

	public static void main(String[] args) {
		
		ProveedorClima cli=new ProveedorOpenWeather();
		System.out.println("La temperatura en este momento: "+ cli.getTemperatura());
		System.out.println("La temperatura del "+LocalDate.now().plus(1, ChronoUnit.DAYS)+" es: "+cli.getTemperaturaDeUnaFecha(LocalDate.now().plus(1, ChronoUnit.DAYS)));
	}

}
