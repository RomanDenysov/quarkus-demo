package com.demo.scheduler;

import io.quarkus.runtime.StartupEvent;
import org.quartz.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class StateScheduler {
    @Inject
    Scheduler scheduler;

    void onStart(@Observes StartupEvent event) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(StateJob.class)
                .withIdentity("stateJob", "stateGroup")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("stateTrigger", "stateGroup")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(30)
                                .repeatForever()
                ).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public static class StateJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            // here could be State Supervisor
            System.out.println("Application is working...");
        }
    }

}
