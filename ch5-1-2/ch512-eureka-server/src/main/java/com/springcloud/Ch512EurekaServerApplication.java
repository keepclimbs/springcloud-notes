package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: song biao wei
 * @date: 2019/1/12 11:37
 * @description:
 */
@SpringBootApplication
@EnableEurekaServer
public class Ch512EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ch512EurekaServerApplication.class, args);
    }
}
