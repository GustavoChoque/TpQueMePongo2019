package testClases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.TelaInvalidaException;
import exceptions.ColoresDePrendaIgualesException;
import modelo.Categoria;
import modelo.Color;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;


public class TestPrenda {
	TipoDePrenda tipoDePrenda,tipoDePrenda2;
	Prenda prenda1,prenda2;	
	
		
	@Before
	public void setUp(){
		
		 tipoDePrenda=new TipoDePrenda(Categoria.PARTE_SUPERIOR,"remera",1,5);
		 tipoDePrenda2=new TipoDePrenda(Categoria.PARTE_INFERIOR,"pantalon",1,10);
		 
		 
	}
	
	@Test
	public void testCreacionPrendaValida() {
		prenda1=new Prenda(tipoDePrenda,Color.AZUL,Tela.ALGODON);
		
		assertEquals("crear Prenda",Tela.ALGODON,prenda1.getTela());
	}
	
	@Test(expected=ColoresDePrendaIgualesException.class)
	public void testMismoColor() {
		prenda2=new Prenda(tipoDePrenda,Color.AZUL,Color.AZUL,Tela.ALGODON);
		
	}
	
	@Test
	public void testTipoPrenda() {
		prenda1=new Prenda(tipoDePrenda,Color.AZUL,Tela.ALGODON);
		assertTrue("Tipo de Prenda",prenda1.getTipoDePrenda()=="remera");
	}
	
	@Test
	public void testCategoria() {
		prenda1=new Prenda(tipoDePrenda,Color.ROJO,Tela.ALGODON);
		assertTrue("Categoria",prenda1.getCategoria().equals(Categoria.PARTE_SUPERIOR));
	}
	@Test(expected=TelaInvalidaException.class)
	public void testTelaInconsistente() {
		TipoDePrenda t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa",2,10);
		Prenda p1=new Prenda(t1,Color.AZUL,Tela.CUERO);
		
	}
	
	@Test
	public void testCompararPrendasIguales(){
		prenda1=new Prenda(tipoDePrenda2,Color.ROJO,Tela.ALGODON);
		prenda2=new Prenda(tipoDePrenda2,Color.ROJO,Tela.ALGODON);
		assertEquals("crear Prenda",prenda1,prenda2);
	}
	
}
