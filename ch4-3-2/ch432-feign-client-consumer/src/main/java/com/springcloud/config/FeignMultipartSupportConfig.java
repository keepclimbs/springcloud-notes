package com.springcloud.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;


/**
 * Feign文件上传Configuration
 */
@Configuration
public class FeignMultipartSupportConfig {

    // 不加这个 文件上传 和 普通的对象接收参数 不能共存
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    // @Primary
    // @Scope("prototype") 书上例子是 原型作用域我想问问为啥
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
}
