package testClases;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modelo.Atuendo;
import modelo.Categoria;
import modelo.Color;
import modelo.Guardaropa;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;

public class TestSugerencias {
	
	Guardaropa g1;
	Prenda rem_roj, cam_az, sho_ama, zap_neg;

	@Before
	public void setUp(){
		
	rem_roj=new Prenda(TipoDePrenda.Remera,Tela.ALGODON, Color.ROJO);
	cam_az=new Prenda(TipoDePrenda.Camisa,Tela.LINO, Color.AZUL);
	sho_ama=new Prenda(TipoDePrenda.Short,Tela.ALGODON, Color.AMARILLO);
	zap_neg=new Prenda(TipoDePrenda.Zapato,Tela.CUERO, Color.NEGRO);
	g1=new Guardaropa();
	g1.agregarPrendaSuperior(rem_roj);
	g1.agregarCalzado(zap_neg);
	g1.agregarPrendaSuperior(cam_az);
	g1.agregarPrendaInferior(sho_ama);
		
			
	}
	
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
	
	
	@Test(expected=NullPointerException.class)
	public void testGuardaropasIncompleto() {
		Guardaropa g2 = new Guardaropa();
		g2.agregarPrendaSuperior(cam_az);
		g2.agregarPrendaInferior(sho_ama);
		
		List<Atuendo> sug = g2.generarSugerencia();
	}
	
	
	@Test
	public void generaTodasLasCombinaciones() {
		Prenda pant_azul = new Prenda(TipoDePrenda.Pantalon,Tela.ALGODON,Color.AZUL);
		g1.agregarPrendaInferior(pant_azul);
		
		assertEquals("2 superiores, 2 inferiores y 1 calzado deberian generar 4 atuendos",4,g1.generarSugerencia().size());
	}
	
	
}
