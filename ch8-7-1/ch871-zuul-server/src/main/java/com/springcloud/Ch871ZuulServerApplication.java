package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: song biao wei
 * @date: 2019/1/30 17:32
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Ch871ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch871ZuulServerApplication.class, args);
    }
}

