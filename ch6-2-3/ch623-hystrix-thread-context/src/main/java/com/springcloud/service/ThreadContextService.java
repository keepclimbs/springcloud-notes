package com.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.config.HystrixThreadLocal;
import com.springcloud.controller.ThreadContextController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class ThreadContextService implements IThreadContextService{
	private static final Logger log = LoggerFactory.getLogger(ThreadContextController.class);

	// 如果没有这个注解 就相当于没有使用Hystrix
   @HystrixCommand
	public String getUser(Integer id) {
		log.info("ThreadContextService, Current thread : " + Thread.currentThread().getId());
		log.info("ThreadContextService, ThreadContext object : " + HystrixThreadLocal.threadLocal.get());
		log.info("ThreadContextService, RequestContextHolder : " + RequestContextHolder.currentRequestAttributes().getAttribute("userId", RequestAttributes.SCOPE_REQUEST));
		return "Success";
	}
}
