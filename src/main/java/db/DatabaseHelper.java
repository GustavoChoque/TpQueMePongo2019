package db;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.mchange.v2.cfg.PropertiesConfigSource.Parse;

import modelo.Gratuito;
import modelo.Guardaropa;
import modelo.Premium;
import modelo.Prenda;
import modelo.Usuario;
import modelo.Evento;
import repositorios.RepositorioTiposDePrenda;

public class DatabaseHelper implements WithGlobalEntityManager, TransactionalOps{
	
	public void inicializarBase(){
		cargarTiposDePrenda();
		cargarUsuarios();
		
	}
	
 private void cargarTiposDePrenda(){
	
		
		EntityManager em=entityManager();
		withTransaction(()->{
			
			RepositorioTiposDePrenda.instance()
			.getTiposPrenda()
			.forEach(tp->em
					.persist(tp));
		});
		
		PerThreadEntityManagers.getEntityManager(); 
		PerThreadEntityManagers.closeEntityManager();
	}

 private void cargarUsuarios(){
		Usuario usuario1,usuario2;
		usuario1=new Usuario(new Gratuito());
		usuario2=new Usuario(new Premium());
		Guardaropa guardaropa1U1,guardaropa2U1,guardaropa1U2,guardaropa2U2;
		guardaropa1U1=new Guardaropa();
		guardaropa1U2=new Guardaropa();
		guardaropa2U1=new Guardaropa();
		guardaropa2U2=new Guardaropa();
		
		usuario1.setUsername("usuario1");
		usuario1.setPassword("123");
		usuario1.setNivelFriolencia(1);
		usuario1.agregarGuardaropa(guardaropa1U1);
		usuario1.agregarGuardaropa(guardaropa2U1);
		

		usuario2.setUsername("usuario2");
		usuario2.setPassword("456");
		usuario2.setNivelFriolencia(1);
		usuario2.agregarGuardaropa(guardaropa1U2);
		usuario2.agregarGuardaropa(guardaropa2U2);
		
		
		EntityManager em=entityManager();
		
		withTransaction(()->{
			
			em.persist(usuario1);
			em.persist(usuario2);
			
			
		});
		
		PerThreadEntityManagers.getEntityManager(); 
		PerThreadEntityManagers.closeEntityManager();	
			
	}
	///----------------------------------------
 /*
	public static Guardaropa getGuardaropaPorId(int id){
		Guardaropa guardaropa=EntityManagerHelper
				.entityManager()
				.createQuery("FROM Guardaropa WHERE id=:idGua",Guardaropa.class)
				.setParameter("idGua", id)
				.getResultList()
				.get(0);
		return guardaropa;
	}
	
	public static List<Integer> listaDeGuardaropas(String idUser){
		List<Guardaropa> idsGuardaropas=EntityManagerHelper.getEntityManager()
				.createQuery("FROM Guardaropa WHERE id_usuario=:idUser",Guardaropa.class)
				.setParameter("idUser", Integer.parseInt(idUser))
				.getResultList();
				
		return idsGuardaropas.stream()
				.map(g->g.getId())
				.collect(Collectors.toList());
	}
	
	public static Usuario obtenerUsuarioPorId(int id){
		
		Usuario usu=null;
		try{
		List<Usuario>usuarios=EntityManagerHelper.entityManager()
		.createQuery("FROM Usuario WHERE id=:idUsuario",Usuario.class)
		.setParameter("idUsuario", id)
		.getResultList();
		if(!usuarios.isEmpty()){
			usu=usuarios.get(0);
		}
		}
		finally{
			
			EntityManagerHelper.entityManager().close();
			
		}
		return usu;
	}
		*/
	/*public static List<String> listaDePrendas(Integer idGuardaropa){
		
		
		List<Prenda> prendas=EntityManagerHelper.getEntityManager()
				.createQuery("FROM Prenda WHERE id_guardaropa=:idGuardaropa",Prenda.class)
				.setParameter("idGuardaropa", idGuardaropa)
				.getResultList();
		
				
		return prendas.stream()
				.map(p-> p.getTipoDePrenda() +", "+ p.getColorP()+ ", " + p.getColorS())
				.collect(Collectors.toList());
	}*/
	/*
	public static List<Evento> listaEventos (String idUsuario)
	{
		List<Evento> eventos=EntityManagerHelper.getEntityManager()
				.createQuery("FROM Evento WHERE usuario_id=:idUsuario",Evento.class)
				.setParameter("idUsuario", idUsuario)
				.getResultList();
		
		return eventos;
	}
	
	public static List<String> parsearEventos (List<Evento> eventos)
	{
		return eventos.stream()
				.map(e-> e.getNombre() + ": "+ e.getFecha().toString())
				.collect(Collectors.toList());
	}
	
	*/
	
}
