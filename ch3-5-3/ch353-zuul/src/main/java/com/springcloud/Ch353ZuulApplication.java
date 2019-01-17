package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: song biao wei
 * @date: 2019/1/12 11:37
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Ch353ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ch353ZuulApplication.class, args);
    }
}
