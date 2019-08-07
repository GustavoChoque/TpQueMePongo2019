package testClases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import auxiliar.Frecuencia;
import modelo.Categoria;
import modelo.Color;
import modelo.Atuendo;
import modelo.Evento;
import modelo.Guardaropa;
import modelo.Premium;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;
import modelo.Usuario;
import repositorios.RepositorioEventos;

public class TestEvento {
	
	TipoDePrenda t1,t2,t3,t4,t5,t6,t7,t8,t9;
	Prenda rem_roj, sinAccesorio, cam_az, sho_ama, zap_neg,jean,p1,p2,anillo;
	Atuendo atuendo;
	Evento evento1,evento2,evento3;
	Usuario usuario1, usuario2;
	Guardaropa guardaropas1,guardaropas2;
	
	
	@Before
	
	public void setUp() {
		
		usuario1 = new Usuario(new Premium());
		usuario2 = new Usuario(new Premium());
		evento1 = new Evento(LocalDate.of(2019, 05, 31),"evento 1", usuario1,null ,Frecuencia.MENSUAL);
		guardaropas1 = new Guardaropa();
		guardaropas2 = new Guardaropa();
		
		t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera",1,5);
		t2=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa",2,10);
		t3=new TipoDePrenda(Categoria.PARTE_INFERIOR, "short",1,5);
		t4=new TipoDePrenda(Categoria.CALZADO, "zapato",1,5);	
		t5=new TipoDePrenda(Categoria.PARTE_INFERIOR, "jean",1,10);
		t6=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa",2,10);
		t7=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "campera",3,20);
		t8=new TipoDePrenda(Categoria.ACCESORIO, "anillo",1,0);
		t9=new TipoDePrenda(Categoria.ACCESORIO, "SinAccesorio",1,0);


		
		p1=new Prenda(t6,Color.AZUL,Tela.ALGODON);
		p2=new Prenda(t7,Color.AZUL,Tela.ALGODON);
		rem_roj=new Prenda(t1, Color.ROJO,Tela.ALGODON);
		cam_az=new Prenda(t2, Color.AZUL,Tela.ALGODON);
		sho_ama=new Prenda(t3, Color.AMARILLO,Tela.ALGODON);
		zap_neg=new Prenda(t4, Color.NEGRO,Tela.CUERO);
		jean = new Prenda(t5,Color.AZUL,Tela.SEDA);
		anillo = new Prenda(t8,Color.AMARILLO,Tela.NINGUNO);
		sinAccesorio = new Prenda(t9,Color.NINGUNO,Tela.NINGUNO);
		
		guardaropas2.agregarPrendaDirecto(rem_roj);
		guardaropas2.agregarPrendaDirecto(cam_az);
		guardaropas2.agregarPrendaDirecto(sho_ama);
		guardaropas2.agregarPrendaDirecto(zap_neg);
		guardaropas2.agregarPrendaDirecto(jean);
		guardaropas2.agregarPrendaDirecto(p2);
		guardaropas2.agregarPrendaDirecto(p1);
		guardaropas2.agregarPrendaDirecto(anillo);
		guardaropas2.agregarPrendaDirecto(sinAccesorio);
		
		atuendo = new Atuendo(rem_roj,p1,p2,sho_ama,zap_neg,sinAccesorio);
	}
	
	//Pruebo si funciona la proximidad del evento
	//Para ambos casos, proximo o no proximo
	
	@Test
	public void eventoEsProximo() {
		
	assertTrue(evento1.esProximo(LocalDate.of(2019, 05, 29)));
	
	assertFalse(evento1.esProximo(LocalDate.of(2019, 01, 01)));
	
	
	}
	
	@Test
	public void eventosRepositorio() {
		usuario1.crearEvento(LocalDate.of(2019, 06, 01), "evento proximo", guardaropas1,Frecuencia.MENSUAL);
		usuario2.crearEvento(LocalDate.of(2019, 06, 01), "evento proximo usuario2", guardaropas1,Frecuencia.MENSUAL);
		usuario1.crearEvento(LocalDate.of(2019, 06, 29), "evento lejano", guardaropas1,Frecuencia.MENSUAL);
		usuario1.crearEvento(LocalDate.of(2019, 06, 01), "evento proximo", guardaropas1,Frecuencia.MENSUAL);
		assertEquals("hay 4 eventos", 4, RepositorioEventos.instance().listaDeEventos.size());
		assertEquals("hay 3 eventos proximos", 3, RepositorioEventos.instance().proximos(LocalDate.of(2019, 05, 29)).size());
		assertEquals("hay 2 eventos proximos del usuario 1", 2, RepositorioEventos.instance().proximos(LocalDate.of(2019, 05, 29)).stream().filter(evento->evento.getNombre() == "evento proximo").collect(Collectors.toList()).size());
		assertEquals("hay 1 evento proximo del usuario 2", 1, RepositorioEventos.instance().proximos(LocalDate.of(2019, 05, 29)).stream().filter(evento->evento.getNombre() == "evento proximo usuario2").collect(Collectors.toList()).size());
	}
	
	@Test
	public void guardaropasCompartido()
	{
		evento3 = new Evento(LocalDate.of(2019, 05, 31),"evento 1", usuario1,guardaropas2 ,Frecuencia.MENSUAL);
		evento3.agregarSugerencia(atuendo);
		evento3.aceptarSugerencia(0);
		assertEquals("Solo quedan 3 prendas disponibles + 1 del sinAccesorio",Arrays.asList(cam_az,jean,anillo,sinAccesorio),guardaropas2.getPrendasDisponibles());
	}

}
