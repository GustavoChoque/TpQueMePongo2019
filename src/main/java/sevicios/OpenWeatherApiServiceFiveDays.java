package sevicios;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenWeatherApiServiceFiveDays {
	@GET("forecast?q=buenos%20aires,ar&APPID=72f45de85d0d2da48e1eeb9c2d3cf6fe")
	Call<OWFiveDaysRespuesta> obtenerDatosClimaDeCincoDias();
}
