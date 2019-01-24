package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
	
    @Autowired
    private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient lbClient;

	@GetMapping("/add")
	public String add(Integer a, Integer b) {
		String result = restTemplate.getForObject("http://ch512-eureka-client/add?a=" + a + "&b=" + b, String.class);
		System.out.println(result);
		return result;
	}

	// 通过实例名 获取实例
	@GetMapping("/add1")
	public void add1(Integer a, Integer b) {
		ServiceInstance instance = this.lbClient.choose("client-a");
		System.out.println(instance.getHost()+":"+instance.getPort());
	}
}