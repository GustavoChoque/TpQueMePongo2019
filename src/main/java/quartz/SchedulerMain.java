package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import modelo.Sugeridor;
import repositorios.RepositorioEventos;
import servicios.ProveedorClima;
import servicios.ProveedorOpenWeather;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.repeatSecondlyForever;
import static org.quartz.TriggerBuilder.newTrigger;

import java.time.LocalDate;

public class SchedulerMain {

		final static Logger logger = LoggerFactory.getLogger(SchedulerMain.class);

	    public static void main(String[] args) throws Exception {
	        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

	        scheduler.start();

	        JobDetail jobDetail = newJob(HelloJob.class).build();

	        Trigger trigger = newTrigger()
	                .startNow()
	                .withSchedule(repeatSecondlyForever(120))
	                .build();

	        scheduler.scheduleJob(jobDetail, trigger);
	}

	    public static class HelloJob implements Job,WithGlobalEntityManager, TransactionalOps{
	    	
	    	private Sugeridor sugeridor=new Sugeridor(new ProveedorOpenWeather());
	    	
	    	@Override
	        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
	    		ejecutar();
	    		logger.info("HelloJob se ejecuto correctamente");
	        }
	    	
	    	
	    	public void ejecutar(){
	    		withTransaction(()->{
	    		
	    		RepositorioEventos.instance()
	    		.proximos(LocalDate.now())
	    		.forEach(evento->evento.sugerir(this.sugeridor));
	    		
	    		});
	    		System.out.println("hola hola");
	    	}

	    }    

}
