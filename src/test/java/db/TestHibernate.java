package db;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

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
	
	

/*
	@Test 
	public void crearPrenda() {
		EntityManager em = entityManager();
		
		TipoDePrenda ti = new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera", 1, 5);
		
		em.persist(ti);
		
		Prenda pr = new Prenda(ti, Color.AZUL, Tela.ALGODON);
		
		em.persist(pr);
		
		em.getTransaction().commit();
		
		
		
	}
	
	@Test
	public void crearUsuario() {
		
		EntityManager em = entityManager();
		
		TipoDeUsuario gr = new Gratuito();
		
		em.persist(gr);
		
		Usuario u1 = new Usuario(gr);
		
		u1.setNivelFriolencia(1234);
		
		em.persist(u1);
		
		Guardaropa gua = new Guardaropa();
		
		gua.setUsuario(u1);
		
		em.persist(gua);
		
		em.getTransaction().commit();
		
	
		
		
	}
	
	@Test 
	public void modificarGuardaropa(){
		
		EntityManager em = entityManager();
		
		Guardaropa g = em.createQuery("from Guardaropa where usuario_id = 1", Guardaropa.class).getResultList().get(0);
		
		Prenda p = em.createQuery("from Prenda where id = 1", Prenda.class).getResultList().get(0);
		
		g.agregarPrenda(p);
		
		em.persist(g);
		
		em.getTransaction().commit();
	}
	
	@Test
	public void crearEvento(){
		
		EntityManager em = entityManager();
		
		TipoDeUsuario gr = new Gratuito();
		
		Usuario u1 = new Usuario(gr);
		
		u1.setNivelFriolencia(1234);
		
		em.persist(u1);
		
		Guardaropa gua = new Guardaropa();
		u1.agregarGuardaropa(gua);
		
		//uso denuevo persist para que guarde el guardaropa nuevo que tiene el usuario,
		//hay que ver bien los cascade , talvez MERGE o REFRESH pueda ser una mejor opcion
		em.persist(u1);
		
		
		LocalDate fecha=LocalDate.of(2019,9,14);
		Evento evento=new Evento(fecha, "Fiesta", u1, u1.getGuardaropas().get(0), Frecuencia.DIARIA);
		em.persist(evento);
		
		em.getTransaction().commit();
	}
	*/
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
	
	

}
