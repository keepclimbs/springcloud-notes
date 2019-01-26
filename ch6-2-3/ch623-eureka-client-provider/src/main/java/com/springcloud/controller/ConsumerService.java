package com.springcloud.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ch623-eureka-client-consumer")
public interface ConsumerService {
	
	@RequestMapping(value = "/helloService", method = RequestMethod.GET)
    String getHelloServiceData();
}
