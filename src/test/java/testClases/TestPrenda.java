package testClases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.TelaInvalidaException;
import modelo.Categoria;
import modelo.Color;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;


public class TestPrenda {
	TipoDePrenda t1,t2,t3;
	Prenda p1,p2;
	List<Prenda> guardaropa;
	
		
	@Before
	public void setUp(){
		
		 guardaropa=new ArrayList<Prenda>();
		 
		 
	}
	
	@Test
	public void testCreacionPrendaValida() {
		t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa",2,10);
		p1=new Prenda(t1,Color.AZUL,Tela.ALGODON);
		guardaropa.add(p1);
		assertEquals("CargarRopa",1,guardaropa.size(),0);
	}
	
	@Test(expected=RuntimeException.class)
	public void testMismoColor() {
		t2=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera",1,5);
		p2=new Prenda(t2,Color.AZUL,Tela.ALGODON);
		p2.setColorSecundario(Color.AZUL);
		
	}
	
	@Test
	public void testTipoPrenda() {
		t2=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera",1,5);
		p1=new Prenda(t2,Color.AZUL,Tela.ALGODON);
		
		assertTrue("Tipo de Prenda",p1.getTipoDePrenda()=="remera");
	}
	
	@Test
	public void testCategoria() {
		t3=new TipoDePrenda(Categoria.PARTE_INFERIOR, "pantalon",1,10);
		p1=new Prenda(t3,Color.AZUL,Tela.ALGODON);
		guardaropa.add(p1);
		assertTrue("Categoria",p1.getCategoria().equals(Categoria.PARTE_INFERIOR));
	}
	@Test(expected=TelaInvalidaException.class)
	public void testTelaInconsistente() {
		t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa",2,10);
		p1=new Prenda(t1,Color.AZUL,Tela.CUERO);
		
	}
	
}
