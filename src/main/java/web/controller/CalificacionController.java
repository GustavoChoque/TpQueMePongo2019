package web.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import modelo.Evento;
import modelo.EventoFinalizado;
import modelo.FeedbackUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class CalificacionController implements WithGlobalEntityManager, TransactionalOps{
	
public String mostrar(Request req,Response res){
		
		String userId=req.cookie("uid");
	
		HashMap<String,Object> viewModel=new HashMap();
		
		LocalDate fechaActual=LocalDate.now();
		
		EntityManager em =entityManager();
		
		List<EventoFinalizado> listaEventosFinalizados=em
				.createQuery("FROM EventoFinalizado WHERE usuario_id=:idUsuario "
						+ "AND YEAR(fecha)<=:anioEvento AND MONTH(fecha)<=:mesEvento AND DAY(fecha)<=:diaEvento",EventoFinalizado.class)
				.setParameter("idUsuario", userId)
				.setParameter("diaEvento", fechaActual.getDayOfMonth())
				.setParameter("mesEvento", fechaActual.getMonthValue())
				.setParameter("anioEvento", fechaActual.getYear())
				.getResultList();
		
		viewModel.put("listaDeEventosFinalizados", listaEventosFinalizados);
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/calificacion/calificacion.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
}

public String getById(Request req,Response res){
	
	String userId=req.cookie("uid");

	HashMap<String,Object> viewModel=new HashMap();
	
	String idEv=req.params("id");
	EntityManager em=entityManager();
	EventoFinalizado evento=em
			.createQuery("FROM EventoFinalizado WHERE id=:idEvento",EventoFinalizado.class)
			.setParameter("idEvento",Integer.parseInt(idEv))
			.getResultList()
			.get(0);
	
	
	viewModel.put("idEvento", evento.getId());
	viewModel.put("nombre", evento.getNombre());
	viewModel.put("fecha", evento.getFecha());
	viewModel.put("sugerenciaElegida", evento.getSugerenciaElegida());
	viewModel.put("puntajes", FeedbackUsuario.values());
	
	
	ModelAndView modelAndView=new ModelAndView(viewModel, "/calificacion/eventoFinalizado.hbs");
	return new HandlebarsTemplateEngine().render(modelAndView);
}

public String calificar(Request req,Response res){
	
	

	HashMap<String,Object> viewModel=new HashMap();
	
	String idEv=req.params("id");
	String calificacion=req.queryParams("inlineRadioOptions");
	
	EntityManager em=entityManager();
	EventoFinalizado evento=em
			.createQuery("FROM EventoFinalizado WHERE id=:idEvento",EventoFinalizado.class)
			.setParameter("idEvento",Integer.parseInt(idEv))
			.getResultList()
			.get(0);
	
	withTransaction(()->{
		evento.calificar(FeedbackUsuario.valueOf(calificacion));
		em.persist(evento);	
		
	});
	

	ModelAndView modelAndView=new ModelAndView(viewModel, "/calificacion/calificarEventoFinalizadoResult.hbs");
	return new HandlebarsTemplateEngine().render(modelAndView);
}


}
