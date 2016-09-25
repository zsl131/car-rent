package com.ztw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by zsl-pc on 2016/9/6.
 */
@SpringBootApplication
@EnableScheduling
public class RootApplication {

    public static void main(String [] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}
