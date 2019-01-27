package com.springcloud.service;

import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;

public interface IHelloService {
	String hello(Integer id, Integer age);
	String getUserToCommandKey(@CacheKey Integer id, Integer age);
	String updateUser(@CacheKey Integer id, Integer age);
}
