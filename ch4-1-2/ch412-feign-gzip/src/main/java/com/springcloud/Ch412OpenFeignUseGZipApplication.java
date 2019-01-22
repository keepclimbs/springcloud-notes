package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: song biao wei
 * @date: 2019/1/12 11:37
 * @description:
 */
@SpringBootApplication
@EnableFeignClients
public class Ch412OpenFeignUseGZipApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ch412OpenFeignUseGZipApplication.class, args);
    }
}
