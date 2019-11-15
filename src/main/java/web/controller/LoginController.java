package web.controller;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import db.EntityManagerHelper;
import modelo.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class LoginController implements WithGlobalEntityManager, TransactionalOps{
	
	public String mostrarLogin(Request req,Response res,String mensaje){
		
		HashMap<String,Object> viewModel=new HashMap();
		viewModel.put("msjError", mensaje);
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "login.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);	
		
	}
	
	public String crear(Request req,Response res){
		String username=req.queryParams("username");
		String pass=req.queryParams("password");
		Usuario usu=obtenerUsuarioPorUsername(username);
		
		if(!usuarioValido(usu,pass)){
			String mensaje="Usuario o Contrseña Incorrecta";
			return mostrarLogin(req, res, mensaje);
			
		}else{
			res.cookie("uid",Integer.toString(usu.getId()));
			res.redirect("/inicio");
			return null;	
		}
		

		
	}
	public String cerrar(Request req,Response res){
		
		res.removeCookie("uid");
		res.redirect("/login");
		return null;
		
	}
	
	
	
	private boolean usuarioValido(Usuario usu,String pass){
		if(usu==null ){
			return false;
		}else{
			return usu.getPassword().equals(pass);
		}
		
	}
	private Usuario obtenerUsuarioPorUsername(String username){
		
		Usuario usu=null;
		
		EntityManager em=entityManager();
		List<Usuario>usuarios=em
		.createQuery("FROM Usuario WHERE username=:nombreUsuario",Usuario.class)
		.setParameter("nombreUsuario", username)
		.getResultList();
		if(!usuarios.isEmpty()){
			usu=usuarios.get(0);
		}
		
		return usu;
	}
	
	
}
