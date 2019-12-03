package com.zhyl;

import com.gbicc.ServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @description: Demo
 * @author: ding
 * @date: 2019/12/2 17:37
 * @version: 1.0
 */

@SpringBootTest(classes = ServiceApplication.class)
@RunWith(SpringRunner.class)
public class Demo {

    @Autowired
    Scheduler scheduler;

    @Autowired
    Trigger trigger;
    @Test
    public void demo() throws SchedulerException, InterruptedException {

    }
}
