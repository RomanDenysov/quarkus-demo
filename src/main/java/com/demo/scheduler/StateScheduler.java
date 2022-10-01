package com.demo.scheduler;

import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
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

    @Slf4j
    public static class StateJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            // here could be State Supervisor
            log.info("Application is working...");
        }
    }

}
