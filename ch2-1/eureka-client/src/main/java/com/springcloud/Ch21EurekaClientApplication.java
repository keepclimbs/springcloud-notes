package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: song biao wei
 * @date: 2019/1/12 11:37
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Ch21EurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ch21EurekaClientApplication.class, args);
    }
}
