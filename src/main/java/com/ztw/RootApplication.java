package com.ztw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by zsl-pc on 2016/9/6.
 */
@SpringBootApplication
@EnableConfigServer
public class RootApplication {

    public static void main(String [] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}
