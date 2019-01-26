package com.springcloud.service.dataservice;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class ProviderServiceCommand extends HystrixCommand<String>{
	
	 	private final String name;
	 	
	    public ProviderServiceCommand(String name) {
			//最少配置:指定命令组名(CommandGroup)
	        super(HystrixCommandGroupKey.Factory.asKey("GroupSC"));
	        this.name = name;
	    }

	    @Override
	    protected String run() {
	        return "Spring Cloud";
	    }
	    
	    @Override
	    protected String getFallback() {
	        return "Failure Spring Cloud";
	    }
	    
}

