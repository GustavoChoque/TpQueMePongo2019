package web.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import auxiliar.Frecuencia;
import db.DatabaseHelper;
import db.EntityManagerHelper;
import modelo.Guardaropa;
import modelo.Usuario;
import modelo.Evento;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class EventoController {
	
public String mostrarEventos(Request req, Response res)
{
		HashMap<String,Object> viewModel=new HashMap();
		
		List<Evento> eventos = DatabaseHelper.listaEventos(req.cookie("uid"));
		viewModel.put("eventosUsuario", DatabaseHelper.parsearEventos(eventos));
		
		
		
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/eventos/listaEventos.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
		

}
	
	
	
	
	
public String agregar(Request req,Response res){
		
		HashMap<String,Object> viewModel=new HashMap();
		
		
		
		viewModel.put("frecuencias", Frecuencia.values());
		viewModel.put("guardaropas", DatabaseHelper.listaDeGuardaropas(req.cookie("uid")));
		
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/eventos/agregarEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
		
		
		
	}


public String crear(Request req,Response res){
	
	HashMap<String,Object> viewModel=new HashMap();
	
	String nombre=req.queryParams("nombre");
	String fecha=req.queryParams("fecha");
	String frecuencia=req.queryParams("frecuencia");
	String idGuardaropa=req.queryParams("guardaropa");
	
	
	Guardaropa guardaropa=DatabaseHelper.getGuardaropaPorId(Integer.parseInt(idGuardaropa));
	Usuario usuario=DatabaseHelper.obtenerUsuarioPorId(Integer.parseInt(req.cookie("uid")));
	
	//ver luego, si poner una transaccion
	
	usuario.crearEvento(LocalDate.parse(fecha), nombre, guardaropa, Frecuencia.valueOf(frecuencia));
	
	
	//viewModel.put("fecha", LocalDate.parse(fecha));
	
	ModelAndView modelAndView=new ModelAndView(viewModel, "/eventos/agregarEventoResultado.hbs");
	return new HandlebarsTemplateEngine().render(modelAndView);
	
	
	
	
}


}
