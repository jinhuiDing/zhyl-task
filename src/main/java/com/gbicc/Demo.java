package com.gbicc;

import com.gbicc.mapper.TaskMapper;
import com.gbicc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: Demo
 * @author: ding
 * @date: 2020/3/15 15:10
 * @version: 1.0
 */

@Component
public class Demo implements ApplicationRunner {

    @Autowired
    TaskMapper taskMapper;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> all = taskMapper.findAll();
        all.forEach(System.out::println);
    }
}
