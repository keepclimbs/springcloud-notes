package com.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1、最好给类HelloFeignServiceConfig放到 spring上下文之外，不然就对所有的feignclient使用（方式1），
 * 想要控制细粒度就不能加 @Configuration注解
 * 2、全局的日志配置方式2 @EnableFeignClients(defaultConfiguration = HelloFeignServiceConfig.class)
 */
@Configuration
public class HelloFeignServiceConfig {

    /**
     feign.Logger: Logger.Level 的具体级别如下：
         NONE：不记录任何信息
         BASIC：仅记录请求方法、URL以及响应状态码和执行时间
         HEADERS：除了记录 BASIC级别的信息外，还会记录请求和响应的头信息
         FULL：记录所有请求与响应的明细，包括头信息、请求体、元数据
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
