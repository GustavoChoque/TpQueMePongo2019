package repositorios;

import java.util.ArrayList;
import java.util.List;

import modelo.Guardaropa;
import modelo.GuardaropaCompartido;

public class RepositorioGuardaropasCompartidos {
	private static RepositorioGuardaropasCompartidos repo;
	private List<Guardaropa> guardaropasCompartidos;
	
	private RepositorioGuardaropasCompartidos(){
		guardaropasCompartidos=new ArrayList<Guardaropa>();		
		
	}
	
	public static RepositorioGuardaropasCompartidos intance(){
		if(repo==null){
			repo=new RepositorioGuardaropasCompartidos();
			
		}
		return repo;
	}
	
	public void agregarGuardaropa(GuardaropaCompartido guardaropa){
		this.guardaropasCompartidos.add(guardaropa);
		
	}
	public void crearGuardaropaCompartido(){
		this.agregarGuardaropa(new GuardaropaCompartido());
		
	}
	
}
