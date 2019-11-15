package web.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

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

public class EventoController implements WithGlobalEntityManager, TransactionalOps{
	
public String mostrarEventos(Request req, Response res)
{
		HashMap<String,Object> viewModel=new HashMap();
		
		List<Evento> eventos = listaEventos(req.cookie("uid"));
		viewModel.put("eventosUsuario", parsearEventos(eventos));
		
		
		
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/eventos/listaEventos.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
		

}
	
	
	
	
	
public String agregar(Request req,Response res){
		
		HashMap<String,Object> viewModel=new HashMap();
		
		
		
		viewModel.put("frecuencias", Frecuencia.values());
		viewModel.put("guardaropas", listaDeGuardaropas(req.cookie("uid")));
		
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/eventos/agregarEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
		
		
		
	}


public String crear(Request req,Response res){
	
	HashMap<String,Object> viewModel=new HashMap();
	
	String nombre=req.queryParams("nombre");
	String fecha=req.queryParams("fecha");
	String frecuencia=req.queryParams("frecuencia");
	String idGuardaropa=req.queryParams("guardaropa");
	
	
	Guardaropa guardaropa=getGuardaropaPorId(Integer.parseInt(idGuardaropa));
	Usuario usuario=obtenerUsuarioPorId(Integer.parseInt(req.cookie("uid")));
	
	//ver luego, si poner una transaccion
	
	usuario.crearEvento(LocalDate.parse(fecha), nombre, guardaropa, Frecuencia.valueOf(frecuencia));
	
	
	//viewModel.put("fecha", LocalDate.parse(fecha));
	
	ModelAndView modelAndView=new ModelAndView(viewModel, "/eventos/agregarEventoResultado.hbs");
	return new HandlebarsTemplateEngine().render(modelAndView);
	
	
	
	
}

public String getById(Request req,Response res){
	
	HashMap<String,Object> viewModel=new HashMap();
	String idEv=req.params("id");
	EntityManager em=entityManager();
	Evento evento=em
			.createQuery("FROM Evento WHERE id=:idEvento",Evento.class)
			.setParameter("idEvento",Integer.parseInt(idEv))
			.getResultList()
			.get(0);
	
	
	
	/*viewModel.put("nombre", evento.getNombre());
	viewModel.put("fecha", evento.getFecha());*/
	
	
	ModelAndView modelAndView=new ModelAndView(evento, "/eventos/evento.hbs");
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

public  Guardaropa getGuardaropaPorId(int id){
	EntityManager em=entityManager();

	Guardaropa guardaropa=em
			.createQuery("FROM Guardaropa WHERE id=:idGua",Guardaropa.class)
			.setParameter("idGua", id)
			.getResultList()
			.get(0);
	return guardaropa;
}

public Usuario obtenerUsuarioPorId(int id){

	Usuario usu=null;
	EntityManager em=entityManager();
	List<Usuario>usuarios=em
	.createQuery("FROM Usuario WHERE id=:idUsuario",Usuario.class)
	.setParameter("idUsuario", id)
	.getResultList();
	if(!usuarios.isEmpty()){
		usu=usuarios.get(0);
	}

	return usu;
}


public List<Evento> listaEventos (String idUsuario)
{	EntityManager em=entityManager();
	List<Evento> eventos=em
			.createQuery("FROM Evento WHERE usuario_id=:idUsuario",Evento.class)
			.setParameter("idUsuario", idUsuario)
			.getResultList();
	
	return eventos;
}

public List<String> parsearEventos (List<Evento> eventos)
{
	return eventos.stream()
			.map(e-> e.getNombre() + ": "+ e.getFecha().toString())
			.collect(Collectors.toList());
}



}
