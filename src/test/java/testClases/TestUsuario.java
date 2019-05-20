package testClases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.Categoria;
import modelo.Color;
import modelo.Guardaropa;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;
import modelo.Usuario;
import repositorios.TiposDePrendas;

public class TestUsuario {
	Usuario u1,u2;
	Guardaropa g1,g2,g3;
	Prenda p1,p2,p3,p4,p5;
	TipoDePrenda t1,t2,t3,t4,t5;
	
	@Before
	public void setUp(){
		
		t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera");
		t2=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa");
		t3=new TipoDePrenda(Categoria.PARTE_INFERIOR, "short");
		t4=new TipoDePrenda(Categoria.CALZADO, "zapato");
		t5=new TipoDePrenda(Categoria.ACCESORIO, "anteojos");
		
		p1=new Prenda(t1, Color.ROJO,Tela.ALGODON);
		p2=new Prenda(t2, Color.AZUL,Tela.ALGODON);
		p2.setColorSecundario(Color.ROJO);
		p3=new Prenda(t3, Color.AMARILLO,Tela.ALGODON);
		p3.setColorSecundario(Color.VERDE);
		p4=new Prenda(t4, Color.NEGRO,Tela.CUERO);
		p5=new Prenda(t5,Color.NEGRO,Tela.NINGUNO);
		g1=new Guardaropa();
		g2=new Guardaropa();
		g3=new Guardaropa();
		
		/*g1.agregarPrendaSuperior(p1);
		g1.agregarCalzado(p4);
		g1.agregarPrendaInferior(p3);
		g1.agregarAccesorio(p5);
		g2.agregarPrendaSuperior(p2);
		g2.agregarPrendaInferior(p3);
		g2.agregarCalzado(p4);
		g3.agregarPrendaInferior(p3);*/
		
		
		g1.getPrendas().add(p1);
		g1.getPrendas().add(p4);
		g1.getPrendas().add(p3);
		g2.getPrendas().add(p2);
		g2.getPrendas().add(p3);
		g2.getPrendas().add(p4);
		g3.getPrendas().add(p3);
		
			
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
		assertEquals("Usuario con atuendos generados por diferentes guardarropas",2,u1.cuantosAtuendosTengo());
	}
	
	
}
