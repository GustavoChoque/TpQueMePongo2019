package web.controller;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class LoginController {
	
public String paginaInicio(Request req,Response res){
		
		HashMap<String,Object> viewModel=new HashMap();
		
		
		
		//viewModel.put("algo", "wwwwwwwwwwww");
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "otro.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
		
		
		
	}
}
