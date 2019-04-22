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
	Prenda p1,p2,p3,p4;
	
	@Before
	public void setUp(){
		
		p1=new Prenda(TipoDePrenda.Remera,Tela.ALGODON, Color.ROJO);
		p2=new Prenda(TipoDePrenda.Camisa,Tela.LINO, Color.AZUL);
		p2.setColorSecundario(Color.ROJO);
		p3=new Prenda(TipoDePrenda.Short,Tela.ALGODON, Color.AMARILLO);
		p3.setColorSecundario(Color.VERDE);
		p4=new Prenda(TipoDePrenda.Zapato,Tela.CUERO, Color.NEGRO);
		g1=new Guardaropa();
		g2=new Guardaropa();
		g3=new Guardaropa();
		
		g1.getPrendas().add(p1);
		g1.getPrendas().add(p4);
		g2.getPrendas().add(p2);
		g3.getPrendas().add(p3);
		
			
	}
	
	@Test
	public void testUsuarioMultiplesGuardaropas(){
		u1=new Usuario();
		u1.getGuardaropas().add(g1);
		u1.getGuardaropas().add(g2);
		
		assertEquals("Usuario con mas de un guardaropa", 2, u1.getGuardaropas().size());
		
		
	}
	
	
}
