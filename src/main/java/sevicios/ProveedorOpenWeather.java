package sevicios;
import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProveedorOpenWeather implements ProveedorClima{
	
	private  Retrofit retrofit;
	private Weather weather;
	private List<Weather> climaPorCincoDias;
	
	public  ProveedorOpenWeather() {
		climaPorCincoDias=new ArrayList<Weather>();
		//Gson gson=new GsonBuilder().serializeNulls().setDateFormat(DateFormat.LONG).create();
		retrofit=new Retrofit.Builder()
				.baseUrl("http://api.openweathermap.org/data/2.5/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		obtenerDatos();
		obtenerDatosDeCincoDias();
		
	}
	
	public void obtenerDatos(){
		
		OpenWeatherApiServiceCurrentDay service=retrofit.create(OpenWeatherApiServiceCurrentDay.class);
		Call<OWCurrentDayRespuesta> openWeatherRespuestaCall=service.obtenerDatosClima();
		Response<OWCurrentDayRespuesta> response;
		try {
			response = openWeatherRespuestaCall.execute();
			OWCurrentDayRespuesta openWeatherRespuesta=response.body();
			//la temperatura nos la dan en kelvin 
			this.weather=new Weather(openWeatherRespuesta.getMain().getTemp(), openWeatherRespuesta.getWind().getSpeed(), openWeatherRespuesta.getClouds().getAll(),LocalDate.now());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRor en retrofit,del response execute");
			
			e.printStackTrace();
			
		}
		
		
	}
	
	public void obtenerDatosDeCincoDias(){
		OpenWeatherApiServiceFiveDays service=retrofit.create(OpenWeatherApiServiceFiveDays.class);
		Call<OWFiveDaysRespuesta> openWeatherRespuestaCall=service.obtenerDatosClimaDeCincoDias();
		Response<OWFiveDaysRespuesta> response;
		try {
			response=openWeatherRespuestaCall.execute();
			OWFiveDaysRespuesta openWeatherRespuesta=response.body();
			openWeatherRespuesta.getList().stream().forEach(o->{
				//ago todo esto porque recibo la fecha en un tipo unix que es tipo 1112255515
				LocalDate auxfecha=LocalDateTime.ofInstant(Instant.ofEpochSecond(o.getDt()), ZoneId.of(ZoneOffset.UTC.getId())).toLocalDate();
				climaPorCincoDias.add(new Weather(o.getMain().getTemp(), auxfecha));
				
			});;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRor en retrofit,del response execute");
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public double getTemperatura() {
				
		return this.weather.getTemperatura();
	}

	@Override
	public double getViento() {
		
		return this.weather.getViento();
	}

	@Override
	public double getProbLluvia() {
		
		return this.weather.getProbLluvia();
	}

	@Override
	public double getTemperaturaDeUnaFecha(LocalDate fecha) {
				
		return climaPorCincoDias.stream()
				.filter(c->c.getFecha().compareTo(fecha)==0)
				.findFirst().get().getTemperatura();
	}

	

}
