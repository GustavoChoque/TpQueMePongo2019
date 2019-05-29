package testClases;

import static org.junit.Assert.*;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import modelo.Evento;
import modelo.Premium;
import modelo.Usuario;

public class TestEvento {
	
	
	Evento evento1;
	Usuario usuario1;
	
	
	@Before
	
	public void setUp() {
		
		usuario1 = new Usuario(new Premium());
		evento1 = new Evento(LocalDate.of(2019, 05, 31),"evento 1", usuario1, null);
		
	}
	
	//Pruebo si funciona la proximidad del evento
	//Para ambos casos, proximo o no proximo
	
	@Test
	public void eventoEsProximo() {
		
	assertTrue(evento1.esProximo(LocalDate.of(2019, 05, 29)));
	
	assertFalse(evento1.esProximo(LocalDate.of(2019, 01, 01)));
	
	
	}

}
