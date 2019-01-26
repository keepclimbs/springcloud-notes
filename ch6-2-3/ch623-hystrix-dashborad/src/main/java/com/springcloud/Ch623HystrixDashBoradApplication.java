package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author: song biao wei
 * @date: 2019/1/24 17:02
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
public class Ch623HystrixDashBoradApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ch623HystrixDashBoradApplication.class, args);
    }
}
