package com.gbicc.config;

import com.gbicc.task.TestTask1;
import com.gbicc.task.TestTask2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author sgl
 * @Date 2018-06-26 16:45
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail testQuartz1() {
        return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1","group").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger1() {
        // 触发器
        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?")).forJob("testTask1","group").withIdentity("trigger","triggerGroup").build();// 触发器名,触发器组

        //5秒执行一次
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(5)
//                .repeatForever();
//        TriggerBuilder.newTrigger().forJob(testQuartz1())
//                .withIdentity("testTask1")
//                .withSchedule(scheduleBuilder)
//                .build();
        return trigger;
    }

    @Bean
    public JobDetail testQuartz2() {
        return JobBuilder.newJob(TestTask2.class).withIdentity("testTask2").storeDurably().build();
    }

//    @Bean
//    public Trigger testQuartzTrigger2() {
//        //cron方式，每隔10秒执行一次
//        return TriggerBuilder.newTrigger().forJob(testQuartz2())
//                .withIdentity("testTask2")
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?"))
//                .build();
//    }


}

