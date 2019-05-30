package modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import repositorios.RepositorioEventos;
import servicios.ProveedorOpenWeather;

public class Usuario {
	
	private List<Guardaropa> guardaropas;
	private List<Atuendo> sugerencias;
	private TipoDeUsuario tipoDeUsuario;
	private List<AtuendoSugerido> historialSugerencias;
	
	
	public Usuario(TipoDeUsuario tipo){
		this.guardaropas=new ArrayList<Guardaropa>();
		this.sugerencias=new ArrayList<Atuendo>();
		this.historialSugerencias=new ArrayList<AtuendoSugerido>();
		this.tipoDeUsuario=tipo;
	}
	
	public List<Guardaropa> getGuardaropas() {
		return guardaropas;
	}
	
	public void haySugerenciasNuevas(List<Atuendo> nuevasSugerencias){
		this.sugerencias=nuevasSugerencias;
	}
	
	public void crearEvento(LocalDate fecha,String nombre,Guardaropa guardaropa){
		Evento nuevoEvento=new Evento(fecha, nombre, this, guardaropa);
		RepositorioEventos.instance().agendar(nuevoEvento);
	}
	
	
	public void agregarGuardaropa(Guardaropa guardaropa) {
		guardaropa.setUsuario(this);
		guardaropas.add(guardaropa);
	}
	
	public void deshacerUltimaOperacion() {
		Atuendo aux = historialSugerencias.get(0).getAtuendo();
		sugerencias.add(aux);		
		historialSugerencias.remove(0);
	}
	
	public void tomarSugerencia() {
		// Por defecto tomo la primera
		Atuendo sugerenciaActual = this.sugerencias.get(0);
		// AL tomarla, la elimino de sugerencias actuales 
		
		// Si acepta, lo agrego con un 1, caso contrario con un dos
		if(this.aceptaSugerencia(sugerenciaActual)) {
			this.sugerenciaAceptada(sugerenciaActual);
		} else {
			this.sugerenciaRechazada(sugerenciaActual);
		}
	}
	
	public List<AtuendoSugerido> mostrarHistorialSegunEleccion(int eleccion){
		return this.historialSugerencias.stream().filter(atuendo -> atuendo.getEleccion() == eleccion).collect(Collectors.toList());
	}
	
	
	// Mostrar el atuendo y darle la opcion al usuario de que lo acepte
	public boolean aceptaSugerencia(Atuendo sugerencia) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Su atuendo esta compuesto por: " + sugerencia.calzado + ", " + sugerencia.prendaInferior + ", " + sugerencia.accesorio );
		System.out.println("Si lo desea aceptar, ingrese 1, caso contrario, ingrese cualquier numero");
		int eleccion = sc.nextInt();
		return eleccion == 1;
	}
	
	public void sugerenciaAceptada(Atuendo sugerencia) {
		this.sugerencias.remove(0);
		AtuendoSugerido aceptado = new AtuendoSugerido(sugerencia,1);
		historialSugerencias.add(aceptado);
	}

	public void sugerenciaRechazada(Atuendo sugerencia) {
		this.sugerencias.remove(0);
		AtuendoSugerido rechazado = new AtuendoSugerido(sugerencia,2);
		historialSugerencias.add(rechazado);
	}
	//------------------------

	
	public int cuantosGuardarropasTengo() {
		return guardaropas.size();
	}
	
	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}
	
	public List<Atuendo> getSugerencias(){
		return sugerencias;
	}
	public List<AtuendoSugerido> getHistorialSugerencias(){
		return historialSugerencias;
	}
}
