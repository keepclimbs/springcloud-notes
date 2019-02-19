package com.springcloud.controller;

import com.springcloud.config.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: song biao wei
 * @date: 2019/2/19 11:28
 * @description:
 */
@RefreshScope
@RestController
public class ConfigClientController {
    @Autowired
    private ConfigInfoProperties configInfoProperties;

    @RequestMapping(value = "/getConfigValue")
    public String getConfigValue() {
        return configInfoProperties.getConfig();
    }
}
