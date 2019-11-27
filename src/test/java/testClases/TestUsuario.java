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
	/*
	Usuario u1,u2;
	
	Guardaropa g1,g2,g3;
	Prenda p1,p2,p3,p4,p5;
	TipoDePrenda t1,t2,t3,t4,t5;
	
	
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
	*/
}
