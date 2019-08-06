package testClases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import auxiliar.Frecuencia;
import modelo.Evento;
import modelo.Guardaropa;
import modelo.Premium;
import modelo.Usuario;
import repositorios.RepositorioEventos;

public class TestEvento {
	
	
	Evento evento1;
	Usuario usuario1, usuario2;
	Guardaropa guardaropas1,guardaropas2;
	
	
	@Before
	
	public void setUp() {
		
		usuario1 = new Usuario(new Premium());
		usuario2 = new Usuario(new Premium());
		evento1 = new Evento(LocalDate.of(2019, 05, 31),"evento 1", usuario1,null ,Frecuencia.MENSUAL);
		guardaropas1 = new Guardaropa();
		guardaropas2 = new Guardaropa();
		
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

}
