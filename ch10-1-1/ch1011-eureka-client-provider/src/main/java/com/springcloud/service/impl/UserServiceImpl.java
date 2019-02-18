package com.springcloud.service.impl;

import com.springcloud.config.UserConfig;
import com.springcloud.context.UserContextHolder;
import com.springcloud.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    private UserConfig userConfig;

    @Override
    public String getDefaultUser() {
        logger.info("provider thread ------------------------ {}", Thread.currentThread().getId());
        return userConfig.getRealname();
    }

    @Override
    public String getContextUserId() {
        return UserContextHolder.currentUser().getUserId();
    }

    @Override
    public List<String> getProviderData() {
        List<String> provider = new ArrayList<String>();
        provider.add("Beijing Company");
        provider.add("Shanghai Company");
        provider.add("tianjin company");
        return provider;
    }
}
