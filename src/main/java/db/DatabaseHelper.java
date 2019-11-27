package db;



import javax.persistence.EntityManager;


import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import auxiliar.TipoxTela;
import modelo.Categoria;
import modelo.Gratuito;
import modelo.Guardaropa;
import modelo.Premium;
import modelo.Tela;
import modelo.TipoDePrenda;
import modelo.Usuario;
import repositorios.RepositorioTiposDePrenda;

public class DatabaseHelper implements WithGlobalEntityManager, TransactionalOps{
	
	public void inicializarBase(){
		cargarTiposDePrenda();
		cargarTelasValidas();
		cargarUsuarios();
		
	}
	
	
	private void cargarTiposDePrenda(){
	
		/*
		EntityManager em=entityManager();
		
		withTransaction(()->{
			
			RepositorioTiposDePrenda.instance()
			.getTiposPrenda()
			.forEach(tp->em
					.persist(tp));
		});
		*/
		
	
		
		TipoDePrenda tipoPrenda1=new TipoDePrenda();
		tipoPrenda1.setNombre("remera");
		tipoPrenda1.setCategoria(Categoria.PARTE_SUPERIOR);
		tipoPrenda1.setCapa(1);
		tipoPrenda1.setNivelDeAbrigo(5);
		
		
		TipoDePrenda tipoPrenda2=new TipoDePrenda();
		tipoPrenda2.setNombre("campera");
		tipoPrenda2.setCategoria(Categoria.PARTE_SUPERIOR);
		tipoPrenda2.setCapa(3);
		tipoPrenda2.setNivelDeAbrigo(20);
		
		TipoDePrenda tipoPrenda3=new TipoDePrenda();
		tipoPrenda3.setNombre("camisa");
		tipoPrenda3.setCategoria(Categoria.PARTE_SUPERIOR);
		tipoPrenda3.setCapa(2);
		tipoPrenda3.setNivelDeAbrigo(10);
		
		TipoDePrenda tipoPrenda4=new TipoDePrenda();
		tipoPrenda4.setNombre("SinSuperior");
		tipoPrenda4.setCategoria(Categoria.PARTE_SUPERIOR);
		tipoPrenda4.setCapa(2);
		tipoPrenda4.setNivelDeAbrigo(0);
		
		TipoDePrenda tipoPrenda5=new TipoDePrenda();
		tipoPrenda5.setNombre("short");
		tipoPrenda5.setCategoria(Categoria.PARTE_INFERIOR);
		tipoPrenda5.setCapa(1);
		tipoPrenda5.setNivelDeAbrigo(5);
		
		TipoDePrenda tipoPrenda6=new TipoDePrenda();
		tipoPrenda6.setNombre("pantalon");
		tipoPrenda6.setCategoria(Categoria.PARTE_INFERIOR);
		tipoPrenda6.setCapa(1);
		tipoPrenda6.setNivelDeAbrigo(10);
		
		TipoDePrenda tipoPrenda7=new TipoDePrenda();
		tipoPrenda7.setNombre("zapato");
		tipoPrenda7.setCategoria(Categoria.CALZADO);
		tipoPrenda7.setCapa(1);
		tipoPrenda7.setNivelDeAbrigo(5);
		
		TipoDePrenda tipoPrenda8=new TipoDePrenda();
		tipoPrenda8.setNombre("anteojos");
		tipoPrenda8.setCategoria(Categoria.ACCESORIO);
		tipoPrenda8.setCapa(1);
		tipoPrenda8.setNivelDeAbrigo(0);
		
		TipoDePrenda tipoPrenda9=new TipoDePrenda();
		tipoPrenda9.setNombre("SinAccesorio");
		tipoPrenda9.setCategoria(Categoria.ACCESORIO);
		tipoPrenda9.setCapa(1);
		tipoPrenda9.setNivelDeAbrigo(0);
		
		EntityManager em=entityManager();
		
		withTransaction(()->{
			em.persist(tipoPrenda1);
			em.persist(tipoPrenda2);
			em.persist(tipoPrenda3);
			em.persist(tipoPrenda4);
			em.persist(tipoPrenda5);
			em.persist(tipoPrenda6);
			em.persist(tipoPrenda7);
			em.persist(tipoPrenda8);
			em.persist(tipoPrenda9);
		});
		
		
		PerThreadEntityManagers.getEntityManager(); 
		PerThreadEntityManagers.closeEntityManager();
	}
	
	
	
	
	private void cargarUsuarios(){
		Usuario usuario1,usuario2,usuario3;
		usuario1=new Usuario(new Gratuito());
		usuario2=new Usuario(new Premium());
		usuario3=new Usuario(new Premium());
		Guardaropa guardaropa1U1,guardaropa2U1,guardaropa1U2,guardaropa2U2,guardaropa1U3;
		guardaropa1U1=new Guardaropa();
		guardaropa1U2=new Guardaropa();
		guardaropa2U1=new Guardaropa();
		guardaropa2U2=new Guardaropa();
		guardaropa1U3=new Guardaropa();
		
		usuario1.setUsername("usuario1");
		usuario1.setPassword("123");
		usuario1.setNivelFriolencia(1);
		usuario1.agregarGuardaropa(guardaropa1U1);
		usuario1.agregarGuardaropa(guardaropa2U1);
		

		usuario2.setUsername("usuario2");
		usuario2.setPassword("456");
		usuario2.setNivelFriolencia(1);
		usuario2.agregarGuardaropa(guardaropa1U2);
		usuario2.agregarGuardaropa(guardaropa2U2);
		
		
		
		usuario3.setUsername("usuario3");
		usuario3.setPassword("789");
		usuario3.setNivelFriolencia(1);
		usuario3.agregarGuardaropa(guardaropa1U3);
		
		
		
		EntityManager em=entityManager();
		
		withTransaction(()->{
			
			em.persist(usuario1);
			em.persist(usuario2);
			em.persist(usuario3);
			
			
		});
		//----------------------
		/*
		Prenda p1,p2,p3,p4,p5,p6,p7,p8;
		TipoDePrenda t1,t2,t3,t4,t5,t6,t7,t8;
		
		t1=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera",1,5);
		t2=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "campera",3,20);
		t3=new TipoDePrenda(Categoria.PARTE_INFERIOR, "short",1,5);
		t4=new TipoDePrenda(Categoria.PARTE_INFERIOR, "pantalon",1,10);
		t5=new TipoDePrenda(Categoria.CALZADO, "zapato",1,5);
		t6=new TipoDePrenda(Categoria.ACCESORIO, "anteojos",1,0);
		t7=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "remera",1,5);
		t8=new TipoDePrenda(Categoria.PARTE_SUPERIOR, "camisa",2,10);
		
		p1=new Prenda(t1, Color.ROJO, Tela.ALGODON);
		p2=new Prenda(t2, Color.AZUL,Color.ROJO, Tela.ALGODON);	
		p3=new Prenda(t3, Color.AMARILLO,Color.VERDE, Tela.ALGODON);	
		p4=new Prenda(t4, Color.VERDE, Tela.ALGODON);
		p5=new Prenda(t5, Color.VERDE, Tela.CUERO);
		p6=new Prenda(t6, Color.ROJO, Tela.NINGUNO);
		p8=new Prenda(t8,Color.AMARILLO,Tela.ALGODON);
		
		guardaropa1U3.agregarPrenda(p1);
		guardaropa1U3.agregarPrenda(p2);
		guardaropa1U3.agregarPrenda(p2);
		guardaropa1U3.agregarPrenda(p3);
		guardaropa1U3.agregarPrenda(p4);
		guardaropa1U3.agregarPrenda(p5);
		guardaropa1U3.agregarPrenda(p6);
		
		
		
		//LocalDate fecha=LocalDate.of(2019,9,29);
		LocalDate fecha=LocalDate.now().plus(3, ChronoUnit.DAYS);
		Evento evento=new Evento(fecha, "Fiesta", usuaPersitido, usuaPersitido.getGuardaropas().get(0), Frecuencia.DIARIA);
		em.persist(evento);
		*/
		//-----------------------------
		
		PerThreadEntityManagers.getEntityManager(); 
		PerThreadEntityManagers.closeEntityManager();	
			
	}
	
	/*
	public static Guardaropa getGuardaropaPorId(int id){
		Guardaropa guardaropa=EntityManagerHelper
				.entityManager()
				.createQuery("FROM Guardaropa WHERE id=:idGua",Guardaropa.class)
				.setParameter("idGua", id)
				.getResultList()
				.get(0);
		return guardaropa;
	}
	
	public static List<Integer> listaDeGuardaropas(String idUser){
		List<Guardaropa> idsGuardaropas=EntityManagerHelper.getEntityManager()
				.createQuery("FROM Guardaropa WHERE id_usuario=:idUser",Guardaropa.class)
				.setParameter("idUser", Integer.parseInt(idUser))
				.getResultList();
				
		return idsGuardaropas.stream()
				.map(g->g.getId())
				.collect(Collectors.toList());
	}
	
	public static Usuario obtenerUsuarioPorId(int id){
		
		Usuario usu=null;
		try{
		List<Usuario>usuarios=EntityManagerHelper.entityManager()
		.createQuery("FROM Usuario WHERE id=:idUsuario",Usuario.class)
		.setParameter("idUsuario", id)
		.getResultList();
		if(!usuarios.isEmpty()){
			usu=usuarios.get(0);
		}
		}
		finally{
			
			EntityManagerHelper.entityManager().close();
			
		}
		return usu;
	}
	*/
	
	public void cargarTelasValidas(){
		
		
		TipoxTela txt1=new TipoxTela("remera",Tela.ALGODON);
		TipoxTela txt2=new TipoxTela("short",Tela.ALGODON);
		TipoxTela txt3=new TipoxTela("pantalon",Tela.ALGODON);
		TipoxTela txt4=new TipoxTela("zapato",Tela.CUERO);
		TipoxTela txt5=new TipoxTela("camisa",Tela.ALGODON);
		TipoxTela txt6=new TipoxTela("camisa",Tela.SEDA);
		TipoxTela txt7=new TipoxTela("campera",Tela.ALGODON);
		TipoxTela txt8=new TipoxTela("anteojos",Tela.NINGUNO);
		TipoxTela txt9=new TipoxTela("SinAccesorio",Tela.NINGUNO);
		TipoxTela txt10=new TipoxTela("SinSuperior",Tela.NINGUNO);
		
		
		EntityManager em=entityManager();
		
		withTransaction(()->{
			em.persist(txt1);
			em.persist(txt2);
			em.persist(txt3);
			em.persist(txt4);
			em.persist(txt5);
			em.persist(txt6);
			em.persist(txt7);
			em.persist(txt8);
			em.persist(txt9);
			em.persist(txt10);
		});
		
		PerThreadEntityManagers.getEntityManager(); 
		PerThreadEntityManagers.closeEntityManager();	
	}
	
}
