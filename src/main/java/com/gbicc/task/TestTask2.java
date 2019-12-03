package com.gbicc.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Description
 * @Author sgl
 * @Date 2018-06-26 16:52
 */
public class TestTask2 extends QuartzJobBean{


    @Autowired
    SchedulerFactoryBean schedulerFactory;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            System.out.println(" 定时任务二执行了" );
            scheduler.start();
        } catch (SchedulerException e) {
        }
    }
}

