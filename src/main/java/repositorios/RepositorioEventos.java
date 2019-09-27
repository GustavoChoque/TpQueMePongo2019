package repositorios;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import db.EntityManagerHelper;
import modelo.Evento;

public class RepositorioEventos {
	private static RepositorioEventos repo;
	public List<Evento> listaDeEventos;
	
	private RepositorioEventos(){
		listaDeEventos=new ArrayList<Evento>();
		
	}
	
	public static RepositorioEventos instance(){
		if (repo==null){
			repo=new RepositorioEventos();
		}
		return repo;
	}
	
	
	public void agendar(Evento evento){
		//listaDeEventos.add(evento);
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.getEntityManager().persist(evento);
		EntityManagerHelper.commit();
	}
	public List<Evento> proximos(LocalDate fecha){
		
		//return this.listaDeEventos.stream().filter(e->e.esProximo(fecha)).collect(Collectors.toList());
	
		List<Evento> eventos=EntityManagerHelper.getEntityManager()
		.createQuery("from Evento",Evento.class)
		.getResultList();
		
	
		return eventos.stream().filter(e->e.esProximo(fecha)).collect(Collectors.toList());
		
	
	
	}
	
}
