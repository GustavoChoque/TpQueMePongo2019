package repositorios;

import java.util.ArrayList;
import java.util.List;


import modelo.Prenda;

public class RepositorioPrendas {
	private static RepositorioPrendas repo;
	public List<Prenda> listaDePrendas;
	
	private RepositorioPrendas(){
		listaDePrendas=new ArrayList<Prenda>();
		
	}
	
	public static RepositorioPrendas instance(){
		if (repo==null){
			repo=new RepositorioPrendas();
		}
		return repo;
	}
}
