package web.controller;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class InicioController {
public String mostrar(Request req,Response res){
		String userId=req.cookie("uid");
		HashMap<String,Object> viewModel=new HashMap();
		viewModel.put("algo", userId);
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "otro.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);	
		
	}
}
