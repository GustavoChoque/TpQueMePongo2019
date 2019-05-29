package quartz;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import repositorios.RepositorioEventos;

public class JobEventos implements Job{
	
	
	public void ejecutar(){
		RepositorioEventos.instance()
		.proximos(LocalDate.now())
		.forEach(evento->evento.sugerir());
		
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		ejecutar();
		//System.out.println("SE ejecuto el job a las: "+LocalDateTime.now());
	}
}
