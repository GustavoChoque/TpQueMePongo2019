package quartz;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import db.EntityManagerHelper;
import modelo.Sugeridor;
import repositorios.RepositorioEventos;
import servicios.ProveedorOpenWeather;

public class JobEventos implements Job,WithGlobalEntityManager, TransactionalOps{
	
	private Sugeridor sugeridor;

	
	public JobEventos() {
		this.sugeridor = new Sugeridor(new ProveedorOpenWeather());
	}
	
	public void setProveedor(Sugeridor sug) {
		this.sugeridor = sug;
	}
	
	public void ejecutar(){
		withTransaction(()->{
		RepositorioEventos.instance()
		.proximos(LocalDate.now())
		.forEach(evento->evento.sugerir(this.sugeridor));
		});
		
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		ejecutar();
		//System.out.println("SE ejecuto el job a las: "+LocalDateTime.now());
	}
}
