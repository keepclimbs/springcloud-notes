package com.springcloud.service.impl;

import com.springcloud.service.FeignService;
import com.springcloud.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: song biao wei
 * @date: 2019/2/18 16:57
 * @description:
 */

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private FeignService feignService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getDefaultUser() {
        logger.info("consumer thread ------------------------ {}", Thread.currentThread().getId());
        return feignService.getDefaultUser();
    }

    @Override
    public String getContextUserId() {
        return feignService.getContextUserId();
    }

    @Override
    public List<String> getProviderData() {
        return restTemplate.getForObject("http://eureka-client-provider/getProviderData", List.class);
    }
}
