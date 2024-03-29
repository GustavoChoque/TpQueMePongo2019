package repositorios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import db.EntityManagerHelper;
import modelo.Categoria;
import modelo.Evento;
import modelo.TipoDePrenda;

public class RepositorioTiposDePrenda implements WithGlobalEntityManager, TransactionalOps {
	
	public static RepositorioTiposDePrenda repo;
	public Set<TipoDePrenda> tiposPrenda;
	
	private RepositorioTiposDePrenda(){
		tiposPrenda=new HashSet<TipoDePrenda>();
		//cargarTiposDePrendas("src/main/resources/tipos");
	}
	
	public static RepositorioTiposDePrenda instance(){
		if(repo==null){
			repo = new RepositorioTiposDePrenda();
		}
		return repo;
	}
	
	
	public void cargarTiposDePrendas(String path){
		
		try {
			FileReader fr=new FileReader(path);
			BufferedReader bf=new BufferedReader(fr);
			String linea;
		try{	
			while((linea=bf.readLine())!=null){
				StringTokenizer subcadena=new StringTokenizer(linea, ",");
				String categoria=subcadena.nextToken();
				String nombreTipoPrenda=subcadena.nextToken();
				int capa=Integer.parseInt(subcadena.nextToken());
				int nivelDeAbrigo=Integer.parseInt(subcadena.nextToken());
				TipoDePrenda tipoPrenda=new TipoDePrenda();
				tipoPrenda.setNombre(nombreTipoPrenda);
				tipoPrenda.setCategoria(categoria);
				tipoPrenda.setCapa(capa);
				tipoPrenda.setNivelDeAbrigo(nivelDeAbrigo);
				tiposPrenda.add(tipoPrenda);
				/*EntityManagerHelper.beginTransaction();
				EntityManagerHelper.getEntityManager().persist(tipoPrenda);
				EntityManagerHelper.commit();*/
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public Set<TipoDePrenda> getTiposPrenda() {
		return tiposPrenda;
	}
	public boolean tipoDePrendaValido(Categoria cat, String nom){
		/*
		TipoDePrenda tipoaux=new TipoDePrenda();
		tipoaux.setCategoria(cat);
		tipoaux.setNombre(nom);
		*/
		EntityManager em=entityManager();
		List<TipoDePrenda> tipos=em
				.createQuery("FROM TipoDePrenda WHERE categoria=:categoria AND nombre=:nombre",TipoDePrenda.class)
				.setParameter("categoria", cat)
				.setParameter("nombre", nom)
				.getResultList();
				
		
		
		//return this.tiposPrenda.contains(tipoaux);
		return !tipos.isEmpty();
	}
	/*public List<TipoDePrenda> traerListaDeTiposDePrendaDesdeBD(){
		
		List<TipoDePrenda> tiposDePrendas=EntityManagerHelper.getEntityManager()
				.createQuery("from TipoDePrenda",TipoDePrenda.class)
				.getResultList();
		return tiposDePrendas;
		
	}*/
}
