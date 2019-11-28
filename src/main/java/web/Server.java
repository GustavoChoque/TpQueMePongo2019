package web;

import db.DatabaseHelper;
import quartz.QuartzSchedulerJobs;
import repositorios.RepositorioTiposDePrenda;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		//inicio base
		new DatabaseHelper().inicializarBase();

		//inicio la tarea progrmada
		QuartzSchedulerJobs sche=new QuartzSchedulerJobs();
		
		//config web server
		//Spark.port(getHerokuAssignedPort());
		
		Spark.port(7000);
		DebugScreen.enableDebugScreen();
		Spark.staticFileLocation("/public");
		
		
		//ruteo dinamico
		Router.instance().cofigurar();
		
		
		
		Spark.init();
	}
	
	
	static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
	
}
