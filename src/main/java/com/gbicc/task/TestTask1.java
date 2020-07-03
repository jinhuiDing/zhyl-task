package com.gbicc.task;

import com.gbicc.mapper.TaskMapper;
import com.gbicc.pojo.User;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Optional;



public class TestTask1 extends QuartzJobBean {

    private static int i = 0;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    SchedulerFactoryBean schedulerFactory;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("i = " + i);
        if (i > 5) {
            System.out.println("超出5次执行未能执行成功");
            //0 0 12 * * ?  每天中午12点执行
            modifyJobTime("testTask1", "group", "trigger", "triggerGroup", "0 0/5 * * * ?");
            System.out.println("=============================今天暂停执行脚本, 修改为5分钟后执行定时任务==========================");
            i=0;
        } else {
            Optional<User> one = taskMapper.findById(1L);
            String query = one.get().getCode();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (query.equals("0")) {

                System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~`开始执行脚本~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~` ");
                try {
                    Runtime runtime = Runtime.getRuntime();
                    String cmd = "cmd /c start D:\\develop\\apache-maven-3.5.2-bin\\cleanLastUpdated.bat";
//                String cmd = "/opt/zhyl-test/provider-8100/stop.sh";
                    Process exec = runtime.exec(cmd);
                    exec.waitFor();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //0 0/2 * * * ?   每2分钟执行一次
                modifyJobTime("testTask1", "group", "trigger", "triggerGroup", "*/30 * * * * ?");

                i=0;
                System.out.println("~~~~~~~~~~~~~~~~~~~脚本执行完成.任务已经执行完成, 修改为30执行定时任务 ");

            } else {
                i++;
                System.out.println("·····························条件未满足,不执行,修改执行任务时间10秒钟执行一次················");
                modifyJobTime("testTask1", "group", "trigger", "triggerGroup", "*/10 * * * * ?");
            }
        }
    }

    /**
     * 移除一个任务
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     */
    public void tomorrory(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            scheduler = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            boolean started = scheduler.isStarted();
            if (started) {
                System.out.println(" 定时任务一马上要被停止 ");
            }
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
        }
    }

    /**
     * @param jobName
     * @param jobGroupName
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param cron             时间设置，参考quartz说明文档
     * @Description: 修改一个任务的触发时间
     */
    public void modifyJobTime(String jobName,
                              String jobGroupName, String triggerName, String triggerGroupName, String cron) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
//
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                sched.rescheduleJob(triggerKey, trigger);
                // 启动
                if (!scheduler.isShutdown()) {
                    scheduler.start();
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

