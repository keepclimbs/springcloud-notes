package com.springcloud.service.dataservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.Charset;

/** 这个类在配置文件里面配置了 */
public class FeignErrorDecoder implements feign.codec.ErrorDecoder{

    @Autowired
    private ObjectMapper objectMapper;

	@Override
	public Exception decode(String methodKey, Response response) {
	 try {
	        // 如果FeignClient里面url写错了 会进入这个判断
            if (response.status() >= 400 && response.status() <= 499) {
                String error = Util.toString(response.body().asReader());
                /** 两段代码等价 error和str相同 */
                String str = StreamUtils.copyToString(response.body().asInputStream(), Charset.forName("UTF-8"));
                return new HystrixBadRequestException(error);
            }
        } catch (IOException e) {
           System.out.println(e);
        }
        return feign.FeignException.errorStatus(methodKey, response);
	}
}

