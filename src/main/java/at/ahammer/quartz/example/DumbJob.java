package at.ahammer.quartz.example;

import org.quartz.*;

public class DumbJob implements Job {

    public DumbJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String url = dataMap.getString("url");
        System.out.println("Instance " + key + " of " + this.getClass().getSimpleName() + " calls: " + url);
    }
}