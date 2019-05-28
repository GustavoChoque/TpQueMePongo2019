package repositorios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import modelo.Categoria;
import modelo.TipoDePrenda;


public class RepositorioTiposDePrenda {
	
	public static RepositorioTiposDePrenda repo;
	public Set<TipoDePrenda> tiposPrenda;
	
	private RepositorioTiposDePrenda(){
		tiposPrenda=new HashSet<TipoDePrenda>();
		cargarTiposDePrendas("src/main/resources/tipos");
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
				TipoDePrenda tipoPrenda=new TipoDePrenda();
				tipoPrenda.setNombre(nombreTipoPrenda);
				tipoPrenda.setCategoria(categoria);
				tiposPrenda.add(tipoPrenda);
			
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
	public boolean tipoDePrendaValido(Categoria cat, String nom, int capa){
		TipoDePrenda tipoaux=new TipoDePrenda();
		tipoaux.setCategoria(cat);
		tipoaux.setNombre(nom);
		tipoaux.setCapa(capa);
		
		return this.tiposPrenda.contains(tipoaux);
	}
}
