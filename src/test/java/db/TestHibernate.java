package db;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import modelo.Color;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;
import repositorios.RepositorioTiposDePrenda;

public class TestHibernate extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	
	/*@Before
	public void setUp() {
		RepositorioPrenda repoPrenda = new RepositorioPrenda();
		
	}
	*/
	/*
	@Test
	public void prendaRegistrada() {
		EntityManager em = entityManager();
		TipoDePrenda tipoPrenda = em.createQuery("from TipoDePrenda where Categoria = 'PARTE_SUPERIOR'",TipoDePrenda.class).getResultList().get(0);
		Prenda prenda = new Prenda(tipoPrenda,Color.AMARILLO,Color.ROJO,Tela.ALGODON);
		em.persist(prenda);
		
		assert.equals(repoPrenda.obtenerPrenda(prenda.getId(),prenda);
		
		
		
	}
	*/
}
