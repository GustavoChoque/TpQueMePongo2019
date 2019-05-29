package testClases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.FaltanteDePrendasException;
import exceptions.GuardarropaIncompletoException;
import modelo.Atuendo;
import modelo.Categoria;
import modelo.Color;
import modelo.Guardaropa;
import modelo.Prenda;
import modelo.Sugeridor;
import modelo.Tela;
import modelo.TipoDePrenda;
import servicios.MockClima;
import servicios.ProveedorOpenWeather;

public class TestSugerencias {
	TipoDePrenda t1,t2,t3,t4,t5;
	Guardaropa g1;
	Prenda rem_roj, cam_az, sho_ama, zap_neg;
	List<Prenda> prendas,prendas2;
	@Before
	public void setUp(){
	
		prendas=new ArrayList<Prenda>();
		prendas2=new ArrayList<Prenda>();
		t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera",1,5);
		t2=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa",2,10);
		t3=new TipoDePrenda(Categoria.PARTE_INFERIOR, "short",1,5);
		t4=new TipoDePrenda(Categoria.CALZADO, "zapato",1,5);	
		
	rem_roj=new Prenda(t1, Color.ROJO,Tela.ALGODON);
	cam_az=new Prenda(t2, Color.AZUL,Tela.ALGODON);
	sho_ama=new Prenda(t3, Color.AMARILLO,Tela.ALGODON);
	zap_neg=new Prenda(t4, Color.NEGRO,Tela.CUERO);
	prendas.add(rem_roj);
	prendas.add(cam_az);
	prendas.add(sho_ama);
	prendas.add(zap_neg);
	
	prendas2.add(rem_roj);
	prendas2.add(cam_az);
	prendas2.add(sho_ama);
	
	
	/*
	g1=new Guardaropa();
	g1.agregarPrendaSuperior(rem_roj);
	g1.agregarCalzado(zap_neg);
	g1.agregarPrendaSuperior(cam_az);
	g1.agregarPrendaInferior(sho_ama);
		*/
			
	}
	/*
	@Test
	public void testSugerenciaValida(){
		
		List<Atuendo> sugerencias = g1.generarSugerencia();
		Atuendo primerSugerencia = sugerencias.get(0);
		Prenda sup = primerSugerencia.getSuperior();
		Prenda inf = primerSugerencia.getInferior();
		Prenda calz = primerSugerencia.getCalzado();
		Prenda acc = primerSugerencia.getAccesorio();
		
		
		assertEquals("La parte superior es de tipo superior", Categoria.PARTE_SUPERIOR, sup.getCategoria());
		
		assertEquals("La parte inferior es de tipo inferior", Categoria.PARTE_INFERIOR, inf.getCategoria());
		
		assertEquals("La parte calzado es de tipo calzado", Categoria.CALZADO, calz.getCategoria());
		
		assertEquals("La parte accesorio es de tipo accesorio", Categoria.ACCESORIO, acc.getCategoria());
	}
	
	@Test(expected=GuardarropaIncompletoException.class)
	public void testGuardaropasIncompleto() {
		Guardaropa g2 = new Guardaropa();
		g2.agregarPrendaSuperior(cam_az);
		g2.agregarPrendaInferior(sho_ama);
		
		List<Atuendo> sug = g2.generarSugerencia();
	}
	
	
	
	
	@Test
	public void generaTodasLasCombinaciones() {
		t5=new TipoDePrenda(Categoria.PARTE_INFERIOR, "pantalon");
		Prenda pant_azul = new Prenda(t5,Color.AZUL,Tela.ALGODON);
		g1.agregarPrendaInferior(pant_azul);
		
		assertEquals("2 superiores, 2 inferiores y 1 calzado deberian generar 4 atuendos",4,g1.generarSugerencia().size());
	}
	*/
	
	
	//Nota: luego cambiar a Mocks
	@Test(expected=FaltanteDePrendasException.class)
	public void testPrendasFaltantes() {
		Sugeridor su=new Sugeridor(new MockClima(00.00, 20.00, 00.00));
		
		List<Atuendo> sug = su.sugerir(prendas2);
	}
	@Test
	public void generaTodasLasCombinaciones() {
		t5=new TipoDePrenda(Categoria.PARTE_INFERIOR, "pantalon",1,10);
		Prenda pant_azul = new Prenda(t5,Color.AZUL,Tela.ALGODON);
		prendas.add(pant_azul);
		Sugeridor su2=new Sugeridor(new MockClima(00.00, 30.00, 00.00));		
		assertEquals("2 superiores(uno capa1 y otro capa2), 2 inferiores y 1 calzado deberian generar 6 atuendos, todas cumplen el criterio",6,su2.sugerir(prendas).size());
	}
	
	@Test
	public void generaTodasLasCombinacionesParaClimaFrio() {
		t5=new TipoDePrenda(Categoria.PARTE_INFERIOR, "pantalon",1,10);
		Prenda pant_azul = new Prenda(t5,Color.AZUL,Tela.ALGODON);
		prendas.add(pant_azul);
		Sugeridor su2=new Sugeridor(new MockClima(00.00, 10.00, 00.00));		
		assertEquals("Igual que el anterior pero solo una cumple el criterio de la temperatura",1,su2.sugerir(prendas).size());
	}
}
