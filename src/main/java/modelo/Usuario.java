package modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	List<Guardaropa> guardaropas;
	
	public Usuario(){
		guardaropas=new ArrayList<Guardaropa>();
	}
	
	public List<Guardaropa> getGuardaropas() {
		return guardaropas;
	}
}
