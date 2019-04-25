package testClases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import modelo.Color;
import modelo.Guardaropa;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;
import modelo.Usuario;

public class TestUsuario {
	Usuario u1,u2;
	Guardaropa g1,g2,g3;
	Prenda p1,p2,p3,p4,p5;
	
	@Before
	public void setUp(){
		
		p1=new Prenda(TipoDePrenda.Remera,Tela.ALGODON, Color.ROJO);
		p2=new Prenda(TipoDePrenda.Camisa,Tela.ALGODON, Color.AZUL);
		p2.setColorSecundario(Color.ROJO);
		p3=new Prenda(TipoDePrenda.Short,Tela.ALGODON, Color.AMARILLO);
		p3.setColorSecundario(Color.VERDE);
		p4=new Prenda(TipoDePrenda.Zapato,Tela.CUERO, Color.NEGRO);
		p5=new Prenda(TipoDePrenda.AnteojosDeSol,Tela.NINGUNO,Color.NEGRO);
		g1=new Guardaropa();
		g2=new Guardaropa();
		g3=new Guardaropa();
		
		g1.agregarPrendaSuperior(p1);
		g1.agregarCalzado(p4);
		g1.agregarPrendaInferior(p3);
		g1.agregarAccesorio(p5);
		g2.agregarPrendaSuperior(p2);
		g2.agregarPrendaInferior(p3);
		g2.agregarCalzado(p4);
		g3.agregarPrendaInferior(p3);
		
			
	}
	
	@Test
	public void testUsuarioMultiplesGuardaropas(){
		u1=new Usuario();
		u1.agregarGuardaropa(g1);
		u1.agregarGuardaropa(g2);
		assertEquals("Usuario con mas de un guardaropa", 2, u1.cuantosGuardarropasTengo());
		
		
	}
	
	@Test
	public void testUsuarioMultiplesAtuendos() {
		u1=new Usuario();
		u1.agregarGuardaropa(g1);
		u1.agregarGuardaropa(g2);
		u1.agregarGuardaropa(g3);
		//el usuario va a terner 2 atuendos de g1, 1 de g2 y 0 de g3. Si tiene un guardaropas que no puede generar atuendos no debería cortar.
		assertEquals("Usuario con atuendos generados por diferentes guardarropas",3,u1.cuantosAtuendosTengo());
	}
	
	
}
