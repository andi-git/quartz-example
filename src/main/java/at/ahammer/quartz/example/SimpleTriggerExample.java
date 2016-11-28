package at.ahammer.quartz.example;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerExample {

    public static void main(String[] args) throws Exception {

        JobDetail job1 = newJob(DumbJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("url", "http:/rest.com/one")
                .build();

        JobDetail job2 = newJob(DumbJob.class)
                .withIdentity("job2", "group1")
                .usingJobData("url", "http:/rest.com/two")
                .build();

        Trigger trigger1 = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();

        Trigger trigger2 = newTrigger()
                .withIdentity("trigger2", "group1")
                .startNow()
                .withSchedule(cronSchedule("*/5 * * * * ?"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job1, trigger1);
        scheduler.scheduleJob(job2, trigger2);
    }
}