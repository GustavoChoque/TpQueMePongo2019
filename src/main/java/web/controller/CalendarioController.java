package web.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.helper.StringHelpers;

import modelo.Evento;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class CalendarioController implements WithGlobalEntityManager, TransactionalOps {
	
	
	public String mostrar(Request req,Response res){
		String userId=req.cookie("uid");
		
		  
		int month=0;
		int year=0;
		try{
		month=Integer.parseInt(req.queryParams("month"));
		year=Integer.parseInt(req.queryParams("year"));
		}catch (NumberFormatException e){
			
			month=LocalDate.now().getMonthValue();
			year=LocalDate.now().getYear();
		}
		
		
		
		HashMap<String,Object> viewModel=new HashMap();
		
		viewModel.put("mes", month);
		viewModel.put("anio", year);
		
		EntityManager em=entityManager();
		
		List<Evento> eventos=em
				.createQuery("FROM Evento WHERE usuario_id=:idUsuario "
						+ "AND MONTH(fecha)=:mesEvento and YEAR(fecha)=:anioEvento",Evento.class)
				.setParameter("idUsuario", userId)
				.setParameter("mesEvento", month)
				.setParameter("anioEvento", year)
				.getResultList();
		
		
		List<List<String>> listasPorDia=new ArrayList<List<String>>();
		/*
		for(int a=0;a<31;a++){
			listasPorDia.add(new ArrayList<String>());	
		}
		*/
		
		//despues agregar un enum o algo para de ahi obtener la cantidad de dias por mes en la var x
		for(int x=1;x<=30;x++){
			listasPorDia.add(armandoListaSegunFecha(LocalDate.of(year, month, x),eventos));
			
		}
		
		
		//viewModel.put("listaEventos", eventos);
		
		viewModel.put("listaEventosPorDia", listasPorDia);
		
		
/*
		Handlebars h=new Handlebars();
		
		h.registerHelper("inc",new Helper<String>() {

			
			@Override
			public CharSequence apply(String valor, Options arg1) throws IOException {
				
				int nuevo=Integer.parseInt(valor)+1;
				
				// TODO Auto-generated method stub
				return Integer.toString(nuevo);
			}
			
		});*/
		
		//StringHelpers.register(h);
		
	
		
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "calendario.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);	
	}
	
	private List<String> armandoListaSegunFecha(LocalDate fecha,List<Evento> listaEventos){
		List<String> nuevaLista=new ArrayList<String>();
		List<Evento> aux=new ArrayList<Evento>();
		
		aux=listaEventos
		.stream()
		.filter(e->e.getFecha().equals(fecha))
		.collect(Collectors.toList());
		
		if(aux.isEmpty()){
			nuevaLista.add("No hay Eventos");
			return nuevaLista;
		}
		
		return aux.stream()
				.map(e->e.getNombre())
				.collect(Collectors.toList());
		
		
	}
	
	
}
