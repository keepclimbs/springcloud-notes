package com.springcloud.service.dataservice;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 这个类在配置文件里面配置了 所以不需要加注解
public class FeignErrorDecoder implements feign.codec.ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
	 try {
            if (response.status() >= 400 && response.status() <= 499) {
                String error = Util.toString(response.body().asReader());
                return new HystrixBadRequestException(error);
            }
        } catch (IOException e) {
           System.out.println(e);
        }
        return feign.FeignException.errorStatus(methodKey, response);
	}
	
}

