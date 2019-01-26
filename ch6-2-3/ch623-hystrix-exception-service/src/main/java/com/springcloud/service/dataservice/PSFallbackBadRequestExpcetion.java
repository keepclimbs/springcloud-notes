package com.springcloud.service.dataservice;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

public class PSFallbackBadRequestExpcetion extends HystrixCommand<String>{

    public PSFallbackBadRequestExpcetion() {
        //最少配置:指定命令组名(CommandGroup)
        super(HystrixCommandGroupKey.Factory.asKey("GroupBRE"));
    }
    
	@Override
	protected String run() throws Exception {
		 throw new HystrixBadRequestException("HystrixBadRequestException error");
	}
	
    @Override
    protected String getFallback() {
    	System.out.println(getFailedExecutionException().getMessage());
        return "invoke HystrixBadRequestException fallback method:  ";
    }
	
	
}
