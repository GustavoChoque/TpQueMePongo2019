package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import quartz.QuartzSchedulerJobs;
import repositorios.RepositorioTipoDePrendaTela;
import repositorios.RepositorioTiposDePrenda;
import servicios.ProveedorClima;
import servicios.ProveedorOpenWeather;

public class Main {

	public static void main(String[] args) {
		
		ProveedorClima cli=new ProveedorOpenWeather();
		System.out.println("La temperatura en este momento: "+ cli.getTemperatura());
		System.out.println("La temperatura del "+LocalDate.now().plus(1, ChronoUnit.DAYS)+" es: "+cli.getTemperaturaDeUnaFecha(LocalDate.now().plus(1, ChronoUnit.DAYS)));
		/*System.out.println(RepositorioTiposDePrenda.instance().getTiposPrenda().size());
		RepositorioTiposDePrenda.instance().getTiposPrenda().forEach(ti->System.out.println(ti.getNombre()+"--"+ti.getCategoria()));
		System.out.println(RepositorioTipoDePrendaTela.getSinglentonInstance().getTipoxTelaValidos().size());
		*/
		
		TipoDePrenda t1,t2,t3,t4,t5,t6,t7,t8;
		Prenda p1,p2,p3,p4,p5,p6;
		List<Prenda> prendas=new ArrayList<Prenda>();
		List<Atuendo> atuendos=new ArrayList<Atuendo>();
		
		
		t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera");
		t2=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "campera");
		t3=new TipoDePrenda(Categoria.PARTE_INFERIOR, "short");
		t4=new TipoDePrenda(Categoria.PARTE_INFERIOR, "pantalon");
		t5=new TipoDePrenda(Categoria.CALZADO, "zapato");
		t6=new TipoDePrenda(Categoria.ACCESORIO, "anteojos");
		t7=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera");
		t8=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa");
		
		p1=new Prenda(t1, Color.ROJO, Tela.ALGODON);
		p2=new Prenda(t2, Color.AZUL,Color.ROJO, Tela.ALGODON);	
		p3=new Prenda(t3, Color.AMARILLO,Color.VERDE, Tela.ALGODON);	
		p4=new Prenda(t4, Color.VERDE, Tela.ALGODON);
		p5=new Prenda(t5, Color.VERDE, Tela.CUERO);
		p6=new Prenda(t6, Color.ROJO, Tela.NINGUNO);
		
		//las prendas se crearon, sin ningun problema, para ver 
		//la excepcion cambiar el nombre en tipoDeprenda
		
		prendas.add(p1);
		prendas.add(p2);
		prendas.add(p3);
		prendas.add(p4);
		prendas.add(p5);
		prendas.add(p6);
		
		Sugeridor su=new Sugeridor(new ProveedorOpenWeather());
		atuendos=su.sugerir(prendas);
		atuendos.forEach(a->System.out.println(a.getSuperior().getTipoDePrenda()+" "+a.getInferior().getTipoDePrenda()+"--"+a.getCalzado().getTipoDePrenda()+"--"+a.getAccesorio().getTipoDePrenda()));
		
		//se ejecuta la tarea Programada
		//QuartzSchedulerJobs sche=new QuartzSchedulerJobs();
		
	}
	
}
