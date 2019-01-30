package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: song biao wei
 * @date: 2019/1/29 13:21
 * @description:
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Ch811EurekaClientApplication1 {
    public static void main(String[] args) {
        // SpringApplication.run(Ch811EurekaClientApplication.class, args);
        SpringApplication.run(Ch811EurekaClientApplication1.class, "--spring.profiles.active=node2");
    }
}
