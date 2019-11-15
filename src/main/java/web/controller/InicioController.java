package web.controller;

import java.util.HashMap;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import modelo.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class InicioController implements WithGlobalEntityManager, TransactionalOps{
public String mostrar(Request req,Response res){
	String userId=req.cookie("uid");
	HashMap<String,Object> viewModel=new HashMap();
	
	EntityManager em=entityManager();
	
	Usuario usuario=em
	.createQuery("FROM Usuario WHERE id=:idUsuario",Usuario.class)
	.setParameter("idUsuario", Integer.parseInt(userId))
	.getResultList()
	.get(0);
	
	viewModel.put("algo", userId);
	viewModel.put("listaNotificaciones", usuario.getNotificaciones());
	
	
	ModelAndView modelAndView=new ModelAndView(viewModel, "otro.hbs");
	return new HandlebarsTemplateEngine().render(modelAndView);	
		
		
	}
}
