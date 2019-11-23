package repositorios;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import db.EntityManagerHelper;
import modelo.Evento;
import modelo.EventoFinalizado;

public class RepositorioEventos implements WithGlobalEntityManager, TransactionalOps{
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
		/*EntityManagerHelper.beginTransaction();
		EntityManagerHelper.getEntityManager().persist(evento);
		EntityManagerHelper.commit();*/
		
		EntityManager em=entityManager();
		withTransaction(()->{
			em.persist(evento);
			
		});
		
		PerThreadEntityManagers.getEntityManager(); 
		PerThreadEntityManagers.closeEntityManager();
		
		
	}
	public List<Evento> proximos(LocalDate fecha){
		
		//return this.listaDeEventos.stream().filter(e->e.esProximo(fecha)).collect(Collectors.toList());
	
		EntityManager em=entityManager();
		
		List<Evento> eventos=em
		.createQuery("from Evento",Evento.class)
		.getResultList();
		
	
		//return eventos.stream().filter(e->e.esProximo(fecha)).collect(Collectors.toList());
		
		List<Evento> listaProximosEventos=eventos.stream().filter(e->e.esProximo(fecha)).collect(Collectors.toList());

		List<Evento> eventosFinalizados=listaProximosEventos.stream()
		.filter(e->e.estaTerminado())
		.collect(Collectors.toList());

		eventosFinalizados.forEach(e->{

			em.persist(new EventoFinalizado(e.getFecha(), e.getNombre(), e.getUsuario(), e.getSugerenciaElegida()));

		});;

		return listaProximosEventos;
	
	}
	
}
