package db;

import static org.junit.Assert.assertEquals;

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
	
	


	@Test
	public void prendaRegistrada() {
		EntityManager em = entityManager();
		TipoDePrenda tipoPrenda = em.createQuery("from TipoDePrenda where descripcion = 'PARTE_SUPERIOR'",TipoDePrenda.class).getResultList().get(0);
		Prenda prenda = new Prenda(tipoPrenda,Color.AMARILLO,Color.ROJO,Tela.ALGODON);
		em.persist(prenda);
		
		
		assertEquals((em.createQuery("from Prenda where id = "+ prenda.getid(),Prenda.class).getResultList().get(0).getid()),prenda.getid());
		
		
		
	}

}
