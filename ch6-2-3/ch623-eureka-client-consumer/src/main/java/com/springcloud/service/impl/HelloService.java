package com.springcloud.service.impl;


import com.springcloud.service.IHelloService;
import com.springcloud.service.dataservice.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class HelloService implements IHelloService {
	
    @Autowired
    private ProviderService dataService;

	@Override
	public List<String> getProviderData() {
		return dataService.getProviderData();
	}

}
