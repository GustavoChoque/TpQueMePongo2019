package web;

import quartz.QuartzSchedulerJobs;
import repositorios.RepositorioTiposDePrenda;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		//inicio los tipodePrenda 
		RepositorioTiposDePrenda.instance();

		//inicio la tarea progrmada
		//QuartzSchedulerJobs sche=new QuartzSchedulerJobs();
		
		//config web server
		Spark.port(7000);
		DebugScreen.enableDebugScreen();
		Spark.staticFileLocation("/public");
		
		//ruteo dinamico
		Router.instance().cofigurar();
		
		
		
		Spark.init();
	}

}
