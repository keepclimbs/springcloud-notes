package com.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: song biao wei
 * @date: 2019/2/19 16:47
 * @description: 定时刷新git配置的定时器
 */
@Component
public class RefreshGitConfigScheDule {

    @Autowired
    private RefreshEndpoint refreshEndpoint;

    @Scheduled(cron = "30 * * * * ?")
    public void sayHello() {
        refreshEndpoint.refresh();
    }
}
