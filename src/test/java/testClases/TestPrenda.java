package testClases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modelo.Categoria;
import modelo.Color;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;


public class TestPrenda {
	
	Prenda p1,p2;
	List<Prenda> guardaropa;
	
		
	@Before
	public void setUp(){
		
		 guardaropa=new ArrayList<Prenda>();
	}
	
	@Test
	public void testCreacionPrendaValida() {
		p1=new Prenda(TipoDePrenda.Camisa,Tela.ALGODON,Color.AZUL);
		guardaropa.add(p1);
		assertEquals("CargarRopa",1,guardaropa.size(),0);
	}
	
	@Test(expected=RuntimeException.class)
	public void testMismoColor() {
		p2=new Prenda(TipoDePrenda.Remera,Tela.ALGODON,Color.AZUL);
		p2.setColorSecundario(Color.AZUL);
		
	}
	
	@Test
	public void testTipoPrenda() {
		p1=new Prenda(TipoDePrenda.Remera,Tela.SEDA,Color.AZUL);
		guardaropa.add(p1);
		assertTrue("Tipo de Prenda",p1.getTipoDePrenda()=="remera");
	}
	
	@Test
	public void testCategoria() {
		p1=new Prenda(TipoDePrenda.Pantalon,Tela.SEDA,Color.AZUL);
		guardaropa.add(p1);
		assertTrue("Categoria",p1.getCategortia().equals(Categoria.PARTE_INFERIOR));
	}
	
}
