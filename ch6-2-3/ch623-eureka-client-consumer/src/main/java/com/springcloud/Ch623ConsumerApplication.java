package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: song biao wei
 * @date: 2019/1/24 16:25
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
public class Ch623ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ch623ConsumerApplication.class, args);
    }
}
