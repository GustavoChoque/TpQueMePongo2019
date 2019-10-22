package web.controller;

import java.util.HashMap;

import com.mchange.v2.cfg.PropertiesConfigSource.Parse;

import auxiliar.Frecuencia;
import db.DatabaseHelper;
import modelo.Guardaropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class GuardaropaController {

	public String mostrarPrendas(Request req, Response res) {
		
		HashMap<String,Object> viewModel=new HashMap();
		
		Integer guardaropasA = DatabaseHelper.listaDeGuardaropas(req.cookie("uid")).get(0);
		Integer guardaropasB = DatabaseHelper.listaDeGuardaropas(req.cookie("uid")).get(1);
		
		
		viewModel.put("prendasA", DatabaseHelper.listaDePrendas(guardaropasA));
		viewModel.put("prendasB", DatabaseHelper.listaDePrendas(guardaropasB));
		
		
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/guardaropa.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
		
		
	}
	
}
