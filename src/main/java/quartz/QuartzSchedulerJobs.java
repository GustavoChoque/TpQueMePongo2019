package quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzSchedulerJobs {
	public QuartzSchedulerJobs(){
		//---------para una tarea programada
				JobDetail mijob=JobBuilder.newJob(JobEventos.class).build();
				/*Class<? extends Job> a=JobEventos.class;
				JobDetail mijob=JobBuilder.newJob(a).build();*/
				
				//es un trigger cada 2 segundos
				Trigger tri=TriggerBuilder.newTrigger().withIdentity("triggerEventos")
						.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(120)
								.repeatForever()).build();
				//este es un trigger con una expresion Cron, puedo tenermas control sobre tiempo de ejecucion
				//en este caso la la expresion dice que se ejecutara cada 12Horas
				/*Trigger tri=TriggerBuilder.newTrigger().withIdentity("triggerEventos")
						.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/12 1/1 * ? *"))
						.build();*/
				try {
					Scheduler s= StdSchedulerFactory.getDefaultScheduler();
					s.start();
					s.scheduleJob(mijob, tri);
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//--------------------
		
	}
}
