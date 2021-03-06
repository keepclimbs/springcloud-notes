package com.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.service.ProviderService;
import com.springcloud.service.dataservice.PSFallbackBadRequestExpcetion;
import com.springcloud.service.dataservice.PSFallbackOtherExpcetion;
import com.springcloud.service.dataservice.ProviderServiceCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试异常是否fallback
 */
@RestController
public class ExceptionController {
    private static Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @Autowired
    private ProviderService providerService;

    @GetMapping
    public List<String> testErrorEncoder() {
        List<String> providerData = providerService.getProviderData();
        return providerData;
    }

    /** 注解方式 使用HystrixCommand */
    @GetMapping("/getFallbackMethodTest")
    @HystrixCommand(fallbackMethod="defaultUser")
    public String getFallbackMethodTest(String id){
        throw new RuntimeException("getFallbackMethodTest failed");
    }

    public String defaultUser(String id, Throwable throwable) {
        log.error(throwable.getMessage());
        return "this is fallback message";
    }

    /** 继承方式 使用HystrixCommand */
    @GetMapping("/getProviderServiceCommand")
    public String providerServiceCommand(){
        // 观察 使用继承的方式 和 注解方式的区别
    	String result = new ProviderServiceCommand("World").execute();
    	return result;
    }
    

    /** BadRequestExpcetion 不会进入熔断 */
    @GetMapping("/getPSFallbackBadRequestExpcetion")
    public String providerServiceFallback(){
    	String result = new PSFallbackBadRequestExpcetion().execute();
    	return result;
    }
    
    
    @GetMapping("/getPSFallbackOtherExpcetion")
    public String pSFallbackOtherExpcetion(){
    	String result = new PSFallbackOtherExpcetion().execute();
    	return result;
    }
}
