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
 * 
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
    
    @GetMapping("/getProviderServiceCommand")
    public String providerServiceCommand(){
    	String result = new ProviderServiceCommand("World").execute();
    	return result;
    }
    

    // BadRequestExpcetion 不会进入熔断
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
    
    @GetMapping("/getFallbackMethodTest")
    @HystrixCommand(fallbackMethod="defaultUser")
    public String getFallbackMethodTest(String id){
    	throw new RuntimeException("getFallbackMethodTest failed");
    }
    
    public String defaultUser(String id, Throwable throwable) {
    	log.error(throwable.getMessage());
        return "this is fallback message";
    }
 
}
