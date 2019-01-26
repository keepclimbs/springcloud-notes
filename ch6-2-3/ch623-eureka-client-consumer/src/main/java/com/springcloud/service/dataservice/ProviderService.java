package com.springcloud.service.dataservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;



@FeignClient(name = "ch623-eureka-client-provider")
public interface ProviderService {
	
	@RequestMapping(value = "/getDashboard", method = RequestMethod.GET)
    List<String> getProviderData();

}
