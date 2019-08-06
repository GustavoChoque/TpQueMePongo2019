package repositorios;

import java.util.ArrayList;
import java.util.List;

import modelo.Atuendo;
//esto esta en verificacion , por ahora estara el repo dentro del usuario, talvez hacer una clase (usuario,sugerencia) 
public class RepositorioSugerenciasPasadas {
	private static RepositorioSugerenciasPasadas repo;
	public List<Atuendo> listaSugerencias;
	
	private RepositorioSugerenciasPasadas(){
		
		this.listaSugerencias=new ArrayList<Atuendo>();
	}
	
	public static RepositorioSugerenciasPasadas instance(){
		
		if(repo==null){
			repo=new RepositorioSugerenciasPasadas();
		}
		return repo;
	}
	
	public void agregarSugerencia(Atuendo atuendo){
		this.listaSugerencias.add(atuendo);
	}
	
	public void agregarConjuntoSugerencias(List<Atuendo> atuendos){
		this.listaSugerencias.addAll(atuendos);
	}
	
	
}
