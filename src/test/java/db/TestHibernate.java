package db;

import static org.junit.Assert.assertEquals;

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

import exceptions.GuardarropaIncompletoException;
import modelo.Categoria;
import modelo.Color;
import modelo.Gratuito;
import modelo.Guardaropa;
import modelo.Premium;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;
import modelo.TipoDeUsuario;
import modelo.Usuario;
import repositorios.RepositorioTiposDePrenda;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHibernate extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	


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
	

}
