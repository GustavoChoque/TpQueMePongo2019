package testClases;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import modelo.Categoria;
import modelo.TipoDePrenda;

public class TestTipoPrenda {
	TipoDePrenda tipoDePrenda1,tipoDePrenda2,tipoDePrenda3,tipoDePrenda4;
	
	@Before
	public void setUp() {
		tipoDePrenda1=new TipoDePrenda();
		
	}
	
	@Test
	public void testCrearTipoPrenda(){
		tipoDePrenda1.setNombre("remera");
		assertEquals("Crear tipo de Prenda", tipoDePrenda1.getNombre(), "remera");
		
	}
	
	@Test(expected=RuntimeException.class)
	public void testCrearTipoPrendaNoValido(){
		tipoDePrenda2=new TipoDePrenda(Categoria.PARTE_SUPERIOR,"zapato",1,5);	
	}
	
	@Test
	public void testCompararTiposDePrendaIguales(){
		tipoDePrenda3=new TipoDePrenda(Categoria.PARTE_SUPERIOR,"remera",1,5);
		tipoDePrenda4=new TipoDePrenda(Categoria.PARTE_SUPERIOR,"remera",1,5);
		assertEquals("Crear tipo de Prenda", tipoDePrenda3, tipoDePrenda4);
	}
}
