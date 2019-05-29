package servicios;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenWeatherApiServiceCurrentDay {
	@GET("weather?q=buenos%20aires,ar&units=metric&APPID=72f45de85d0d2da48e1eeb9c2d3cf6fe")
	Call<OWCurrentDayRespuesta> obtenerDatosClima();
}
