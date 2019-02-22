package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: song biao wei
 * @date: 2019/1/29 13:23
 * @description: 限流的启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Ch811ZuulRatelimitApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ch811ZuulRatelimitApplication.class, "--spring.profiles.active=ratelimit");
    }
}
