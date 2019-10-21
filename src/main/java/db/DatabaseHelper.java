package db;

import modelo.Gratuito;
import modelo.Guardaropa;
import modelo.Premium;
import modelo.Usuario;
import repositorios.RepositorioTiposDePrenda;

public class DatabaseHelper {
	
	public static void inicializarBase(){
		cargarTiposDePrenda();
		cargarUsuarios();
		
	}
	
	private static void cargarTiposDePrenda(){
		
		try{
		EntityManagerHelper.entityManager().getTransaction().begin();
		
		RepositorioTiposDePrenda.instance()
		.getTiposPrenda()
		.forEach(tp->EntityManagerHelper
				.entityManager()
				.persist(tp));
		
		EntityManagerHelper.entityManager().getTransaction().commit();
		
		}catch (Exception e) {
			EntityManagerHelper.entityManager().getTransaction().rollback();
		}
		
		EntityManagerHelper.entityManager().close();
	}
	private static void cargarUsuarios(){
		Usuario usuario1,usuario2;
		usuario1=new Usuario(new Gratuito());
		usuario2=new Usuario(new Premium());
		Guardaropa guardaropa1U1,guardaropa2U1,guardaropa1U2,guardaropa2U2;
		guardaropa1U1=new Guardaropa();
		guardaropa1U2=new Guardaropa();
		guardaropa2U1=new Guardaropa();
		guardaropa2U2=new Guardaropa();
		
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
		
		
		
		try{
			EntityManagerHelper.entityManager().getTransaction().begin();
		
			EntityManagerHelper.entityManager().persist(usuario1);
			EntityManagerHelper.entityManager().persist(usuario2);
			
		
			EntityManagerHelper.entityManager().getTransaction().commit();
		}
		catch (Exception e) {
			EntityManagerHelper.entityManager().getTransaction().rollback();
		}
		finally{
			EntityManagerHelper.entityManager().close();
		}
	}
	
	
	
}
