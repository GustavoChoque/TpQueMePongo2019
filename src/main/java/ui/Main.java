package ui;

import db.DatabaseHelper;
import db.EntityManagerHelper;
import modelo.Gratuito;
import modelo.Guardaropa;
import modelo.Usuario;
import quartz.QuartzSchedulerJobs;

public class Main {

	public static void main(String[] args) {
		
		new DatabaseHelper().inicializarBase();
		Helper h=new Helper();
		Usuario usuario=h.getUsuarioPorId(1);
		
		QuartzSchedulerJobs sche=new QuartzSchedulerJobs();
		
	    new QueMePongoView(usuario).startApplication();
	}

}
