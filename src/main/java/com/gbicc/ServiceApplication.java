package com.gbicc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: ServiceApplication
 * @author: ding
 * @date: 2019/11/25 14:43
 * @version: 1.0
 */

@EnableScheduling
@SpringBootApplication
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }


}
