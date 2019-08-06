package ui;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.annotations.Observable;

import modelo.Color;
import modelo.Evento;
import modelo.Gratuito;
import modelo.Guardaropa;
import modelo.Usuario;



@Observable
public class QueMePongoViewModel {
	
	private Usuario usuario;
	private String mensaje;
	private int cantGuardaropas;
	private int cantPrendasTotales;
	private List<Color> colores;
	private Evento notificacionElegida;
	
	
	public QueMePongoViewModel(Usuario usuario){
		//luego eltipo del usuario se lo obtendra de la DB
		this.usuario=usuario;
		
		this.mensaje="Esto es un mensaje";
		
		
		this.colores=Arrays.asList(Color.values());
	}
	
	
	
	public int cantidaDeGuardaropas() {
		return usuario.getGuardaropas().size();

	}
	
	public int cantidadDePrendasTotales(){
		return usuario.getGuardaropas().stream().mapToInt(g->g.getPrendas().size()).sum();
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCantGuardaropas() {
		return cantGuardaropas;
	}

	public void setCantGuardaropas(int cantGuardaropas) {
		this.cantGuardaropas = cantGuardaropas;
	}

	public int getCantPrendasTotales() {
		return cantPrendasTotales;
	}

	public void setCantPrendasTotales(int cantPrendasTotales) {
		this.cantPrendasTotales = cantPrendasTotales;
	}



	public List<Color> getColores() {
		return colores;
	}



	public void setColores(List<Color> colores) {
		this.colores = colores;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Evento getNotificacionElegida() {
		return notificacionElegida;
	}



	public void setNotificacionElegida(Evento notificacionElegida) {
		this.notificacionElegida = notificacionElegida;
	}

	
}
