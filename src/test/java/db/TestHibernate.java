package db;

import static org.junit.Assert.assertEquals;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import auxiliar.Frecuencia;
import exceptions.GuardarropaIncompletoException;
import modelo.Categoria;
import modelo.Color;
import modelo.Evento;
import modelo.Gratuito;
import modelo.Guardaropa;
import modelo.Premium;
import modelo.Prenda;
import modelo.Sugeridor;
import modelo.Tela;
import modelo.TipoDePrenda;
import modelo.TipoDeUsuario;
import modelo.Usuario;
import repositorios.RepositorioEventos;
import repositorios.RepositorioTiposDePrenda;
import servicios.ProveedorOpenWeather;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHibernate extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	
	
	@Test
	public void crearEvento(){
		
		EntityManager em = entityManager();
		
		TipoDeUsuario gr = new Gratuito();
		
		Usuario u1 = new Usuario(gr);
		
		u1.setNivelFriolencia(1234);
		
		Guardaropa g = new Guardaropa();
		
		Evento evento = new Evento(LocalDate.now(), "EventoPrueba" ,u1 , g, Frecuencia.DIARIA);
		
		withTransaction(()->{
			em.persist(g);
			em.persist(u1);
			em.persist(evento);
	}
			);
		
		
		List<Evento> eventos = em.createQuery("from Evento").getResultList();
		assertEquals("Cantidad de eventos", 1 ,eventos.size());
		
	}
	
	@Test
	public void eliminarEvento() {
		
		EntityManager em = entityManager();
		
		TipoDeUsuario gr = new Gratuito();
		
		Usuario u1 = new Usuario(gr);
		
		u1.setNivelFriolencia(1234);
		
		Guardaropa g = new Guardaropa();
		
		Evento evento = new Evento(LocalDate.now(), "EventoPrueba" ,u1 , g, Frecuencia.DIARIA);
		
		withTransaction(()->{
			em.persist(g);
			em.persist(u1);
			em.persist(evento);
	}
			);
		
		
		Evento persistido = em.createQuery("from Evento where usuario_id = :userId", Evento.class).setParameter("userId", u1.getId()).getResultList().get(0);
		
		
		withTransaction(() ->{
		em.remove(persistido);
			
		}
				);
		
		List<Evento> eventos = em.createQuery("from Evento where usuario_id = :userId", Evento.class).setParameter("userId", u1.getId()).getResultList();
		
		
		assertEquals("Agregar y borrar un evento", 0, eventos.size() );
		
	}

	
	
	@Test 
	public void modificarGuardaropa(){
		
		EntityManager em = entityManager();
		
		Guardaropa g = em.createQuery("from Guardaropa", Guardaropa.class).getResultList().get(0);
		
		TipoDeUsuario gratis = new Gratuito();
		
		Usuario userNuevo = new Usuario(gratis);
		
		withTransaction(()->{
			em.persist(gratis);
			em.persist(userNuevo);
			g.setUsuario(userNuevo);
			em.persist(g);
	}
			);
		
		List<Guardaropa> persistidos = em.createQuery("from Guardaropa where usuario_id = :userId", Guardaropa.class).setParameter("userId",userNuevo.getId()).getResultList();
		assertEquals("Cantidad de guardaropa de nuevo usuario",1,persistidos.size());
		
		


	}
	


	@Test 
	public void crearYGuardarPrenda() {
		EntityManager em = EntityManagerHelper.entityManager();
		
		
		TipoDePrenda ti = new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera", 1, 5);
		Prenda pr = new Prenda(ti, Color.AZUL, Tela.ALGODON);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(ti);
	
		em.persist(pr);
		
		tx.commit();
		
		em.close();
			
		
	}
	
	@Test
	public void crearUsuario() {
		
		EntityManager em = entityManager();
		
		TipoDeUsuario gr = new Gratuito();
		
		Usuario u1 = new Usuario(gr);
		
		u1.setNivelFriolencia(9999);
		
		Guardaropa gua = new Guardaropa();
		
		gua.setUsuario(u1);
		
		
		withTransaction(()->{
			em.persist(gr);
			em.persist(u1);
			em.persist(gua);
	}
			);
		

		float nivel = 9999;
		List<Usuario> usuarios = em.createQuery("from Usuario u where u.nivelFriolencia = :nivel", Usuario.class).setParameter("nivel", nivel).getResultList();
		assertEquals("Usuarios con nivel de friolencia 9999",1,usuarios.size());
		
		
	}
		
	
	
	


	
	@Test
	public void guardarSugerencias(){
		
		EntityManager em = entityManager();
		
		
		TipoDeUsuario gr = new Gratuito();
		
		Usuario usuaPersitido = new Usuario(gr);
		
		Guardaropa gua = new Guardaropa();
		
		usuaPersitido.agregarGuardaropa(gua);
		em.persist(usuaPersitido);
		
		
		Prenda p1,p2,p3,p4,p5,p6,p7,p8;
		TipoDePrenda t1,t2,t3,t4,t5,t6,t7,t8;
		
		t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera",1,5);
		t2=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "campera",3,20);
		t3=new TipoDePrenda(Categoria.PARTE_INFERIOR, "short",1,5);
		t4=new TipoDePrenda(Categoria.PARTE_INFERIOR, "pantalon",1,10);
		t5=new TipoDePrenda(Categoria.CALZADO, "zapato",1,5);
		t6=new TipoDePrenda(Categoria.ACCESORIO, "anteojos",1,0);
		t7=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera",1,5);
		t8=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa",2,10);
		
		p1=new Prenda(t1, Color.ROJO, Tela.ALGODON);
		p2=new Prenda(t2, Color.AZUL,Color.ROJO, Tela.ALGODON);	
		p3=new Prenda(t3, Color.AMARILLO,Color.VERDE, Tela.ALGODON);	
		p4=new Prenda(t4, Color.VERDE, Tela.ALGODON);
		p5=new Prenda(t5, Color.VERDE, Tela.CUERO);
		p6=new Prenda(t6, Color.ROJO, Tela.NINGUNO);
		p8=new Prenda(t8,Color.AMARILLO,Tela.ALGODON);
		
		gua.agregarPrenda(p1);
		gua.agregarPrenda(p2);
		gua.agregarPrenda(p2);
		gua.agregarPrenda(p3);
		gua.agregarPrenda(p4);
		gua.agregarPrenda(p5);
		gua.agregarPrenda(p6);
		
		
		
		//LocalDate fecha=LocalDate.of(2019,9,29);
		LocalDate fecha=LocalDate.now().plus(3, ChronoUnit.DAYS);
		Evento evento=new Evento(fecha, "Fiesta", usuaPersitido, usuaPersitido.getGuardaropas().get(0), Frecuencia.DIARIA);
		em.persist(evento);
		em.getTransaction().commit();
		
		
		
		
		
		EntityManagerHelper.beginTransaction();
		RepositorioEventos.instance()
		.proximos(LocalDate.now())
		.forEach(e->e.sugerir(new Sugeridor(new ProveedorOpenWeather())));
		EntityManagerHelper.commit();
		
		
		
	}
	
	@Test
	public void AgregarUsuario(){
		Usuario usu=new Usuario(new Gratuito());
		Guardaropa g1=new Guardaropa();
		usu.agregarGuardaropa(g1);
		EntityManager em=entityManager();
		withTransaction(()->{
				em.persist(usu);
		}
				);
		List<Usuario>usuarios=em.createQuery("from Usuario").getResultList();
		assertEquals("Cantidad usuarios", 1, usuarios.size());
	}
	
	@Test
	public void AgregarPrenda(){
		TipoDePrenda tipoDePrenda=new TipoDePrenda(Categoria.PARTE_SUPERIOR,"remera",1,5);
		Prenda prenda=new Prenda(tipoDePrenda,Color.AZUL,Tela.ALGODON);
		EntityManager em=entityManager();
		withTransaction(()->{
				em.persist(prenda);
		}
				);
		List<Prenda>prendas=em.createQuery("from Prenda").getResultList();
		assertEquals("Cantidad prendas", 1, prendas.size());
	}
	
	


}
