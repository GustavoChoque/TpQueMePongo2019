package modelo;

import java.time.LocalDate;

import repositorios.RepositorioEventos;

public class JobEventos {
	
	
	public void ejecutar(){
		RepositorioEventos.instance()
		.proximos(LocalDate.now())
		.forEach(evento->evento.sugerir());
		
	}
}
