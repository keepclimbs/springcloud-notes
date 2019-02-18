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
        // 输出异常信息 注解方式是增加参数 Throwable throwable 请看下面注释代码
    	System.out.println(getFailedExecutionException().getMessage());
        return "invoke HystrixBadRequestException fallback method:  ";
    }

    /*@GetMapping("/getFallbackMethodTest")
    @HystrixCommand(fallbackMethod="defaultUser")
    public String getFallbackMethodTest(String id){
        throw new RuntimeException("getFallbackMethodTest failed");
    }

    public String defaultUser(String id, Throwable throwable) {
        log.error(throwable.getMessage());
        return "this is fallback message";
    }*/
	
	
}
