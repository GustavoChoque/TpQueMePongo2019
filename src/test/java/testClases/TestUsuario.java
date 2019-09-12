package testClases;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.LimiteListaException;
import modelo.Atuendo;
import modelo.Categoria;
import modelo.Color;
import modelo.Gratuito;
import modelo.Guardaropa;
import modelo.Premium;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;
import modelo.Usuario;


public class TestUsuario {
	Usuario u1,u2;
	
	Guardaropa g1,g2,g3;
	Prenda p1,p2,p3,p4,p5;
	TipoDePrenda t1,t2,t3,t4,t5;
	Atuendo a1,a2; 
	
	@Before
	public void setUp(){
		
		t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera",1,5);
		t2=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa",2,10);
		t3=new TipoDePrenda(Categoria.PARTE_INFERIOR, "short",1,5);
		t4=new TipoDePrenda(Categoria.CALZADO, "zapato",1,5);
		t5=new TipoDePrenda(Categoria.ACCESORIO, "anteojos",1,0);
		
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
		
		u2 = new Usuario(new Premium());
		//a1 = new Atuendo (Arrays.asList(p1),p3,p4,p5);
		//a2 = new Atuendo (Arrays.asList(p2),p3,p4,p5);
		
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
		u1=new Usuario(new Premium());
		u1.agregarGuardaropa(g1);
		u1.agregarGuardaropa(g2);
		assertEquals("Usuario con mas de un guardaropa", 2, u1.getGuardaropas().size());
		
		
	}
	
	/*@Test
	public void testUsuarioMultiplesAtuendos() {
		u1=new Usuario(new Gratuito());
		u1.agregarGuardaropa(g1);
		u1.agregarGuardaropa(g2);
		u1.agregarGuardaropa(g3);
		//el usuario va a terner 2 atuendos de g1, 1 de g2 y 0 de g3. Si tiene un guardaropas que no puede generar atuendos no debería cortar.
		assertEquals("Usuario con atuendos generados por diferentes guardarropas",2,u1.cuantosAtuendosTengo());
	}*/
	
	@Test(expected=LimiteListaException.class)
	public void testGuardaropaDeUsuarioGratuito(){
		//los guardaropas de usuarios gratuitos tiene un limite de 10 prendas
		u1=new Usuario(new Gratuito());
		Guardaropa g1=new Guardaropa();
		u1.agregarGuardaropa(g1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		u1.getGuardaropas().get(0).agregarPrenda(p1);
		
	}
	/*
	@Test
	public void testLlegaSugerencia() {
		
		u2.haySugerenciasNuevas(Arrays.asList(a1));
		assertEquals("se agrega a las sugerencias del usuario el atuendo a1",a1,u2.getSugerencias().get(0));
	}
	
	@Test
	public void testAceptarSugerencia() {
		List<Atuendo> auxiliar = new ArrayList<Atuendo>();
		auxiliar.add(a2);
		u2.haySugerenciasNuevas(auxiliar);
		u2.sugerenciaAceptada(a2);
		assertEquals("La sugerencia pasa al historial de sugerencias",1,u2.getHistorialSugerencias().size());
	}
	
	@Test
	public void testDeshacerOperacionAnterior() {
		List<Atuendo> auxiliar = new ArrayList<Atuendo>();
		auxiliar.add(a2);
		u2.haySugerenciasNuevas(auxiliar);
		u2.sugerenciaAceptada(a2);
		u2.deshacerUltimaOperacion();
		assertEquals("El atuendo debe haber vuelto a la lista de sugerencias",1,u2.getSugerencias().size());
		assertEquals("El historial de sugerencias debe estar vacio de vuelta",0,u2.getHistorialSugerencias().size());
	}*/
	
	/*@Test
	public void testUsuarioGratuitoGuardaropasLleno() {
		u3 = new UsuarioGratuito(2);
		u3.agregarGuardaropa(g1);
		u3.agregarGuardaropa(g2);
		assertFalse(u3.puedeAgregarGuardaropa());
	}*/
}
