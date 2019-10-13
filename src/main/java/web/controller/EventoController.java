package web.controller;

import java.util.HashMap;

import auxiliar.Frecuencia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class EventoController {
public String agregar(Request req,Response res){
		
		HashMap<String,Object> viewModel=new HashMap();
		
		
		
		viewModel.put("frecuencias", Frecuencia.values());
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/eventos/agregarEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
		
		
		
	}
}
