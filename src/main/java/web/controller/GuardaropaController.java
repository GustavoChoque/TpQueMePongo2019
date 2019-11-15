package web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.mchange.v2.cfg.PropertiesConfigSource.Parse;

import auxiliar.Frecuencia;
import db.DatabaseHelper;
import db.EntityManagerHelper;
import modelo.Guardaropa;
import modelo.Prenda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class GuardaropaController implements WithGlobalEntityManager, TransactionalOps{

	public String mostrarPrendas(Request req, Response res) {
		
		HashMap<String,Object> viewModel=new HashMap();
		
		Integer guardaropasA = listaDeGuardaropas(req.cookie("uid")).get(0);
		Integer guardaropasB = listaDeGuardaropas(req.cookie("uid")).get(1);
		
		
		viewModel.put("prendasA", listaDePrendas(guardaropasA));
		viewModel.put("prendasB", listaDePrendas(guardaropasB));
		
		
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/guardaropa.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
		
		
	}
	
	public List<Integer> listaDeGuardaropas(String idUser){
		EntityManager em=entityManager();
		List<Guardaropa> idsGuardaropas=em
				.createQuery("FROM Guardaropa WHERE id_usuario=:idUser",Guardaropa.class)
				.setParameter("idUser", Integer.parseInt(idUser))
				.getResultList();
				
		return idsGuardaropas.stream()
				.map(g->g.getId())
				.collect(Collectors.toList());
	}
	
public List<String> listaDePrendas(Integer idGuardaropa){
		
		EntityManager em=entityManager();
		List<Prenda> prendas=em
				.createQuery("FROM Prenda WHERE id_guardaropa=:idGuardaropa",Prenda.class)
				.setParameter("idGuardaropa", idGuardaropa)
				.getResultList();
		
				
		return prendas.stream()
				.map(p-> p.getTipoDePrenda() +", "+ p.getColorP()+ ", " + p.getColorS())
				.collect(Collectors.toList());
	}
}
