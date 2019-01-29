package com.springcloud;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;
import com.springcloud.filter.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: song biao wei
 * @date: 2019/1/29 13:23
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Ch811ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ch811ZuulApplication.class, args);
    }

    /**
     * Groovy加载方法配置，20秒自动刷新
     */
    @Component
    public static class GroovyRunner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            MonitoringHelper.initMocks();
            FilterLoader.getInstance().setCompiler(new GroovyCompiler());
            try {
                String groovyPath = "G:\\git_code\\springcloud-notes\\ch8-1-1\\ch811-zuul\\src\\main\\java\\com\\springcloud\\groovy";
                FilterFileManager.setFilenameFilter(new GroovyFileFilter());
                FilterFileManager.init(20, groovyPath);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Bean
    public FirstPreFilter firstPreFilter(){
  	return new FirstPreFilter();
    }

    @Bean
    public SecondPreFilter secondPreFilter(){
        return new SecondPreFilter();
    }

    @Bean
    public ThirdPreFilter thirdPreFilter(){
        return new ThirdPreFilter();
    }

    @Bean
    public ErrorFilter errorFilter(){
    return new ErrorFilter();
    }

    @Bean
    public PostFilter postFilter(){
        return new PostFilter();
    }
}
