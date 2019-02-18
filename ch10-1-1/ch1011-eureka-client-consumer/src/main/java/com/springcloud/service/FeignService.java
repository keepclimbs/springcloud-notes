package com.springcloud.service;

import com.springcloud.service.fallback.FeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: song biao wei
 * @date: 2019/2/18 17:12
 * @description:
 */

@FeignClient(name = "eureka-client-provider", fallback = FeignFallBack.class)
public interface FeignService {

    @RequestMapping(value = "/getDefaultUser", method = RequestMethod.GET)
    String getDefaultUser();

    @RequestMapping(value = "/getContextUserId", method = RequestMethod.GET)
    String getContextUserId();

}
