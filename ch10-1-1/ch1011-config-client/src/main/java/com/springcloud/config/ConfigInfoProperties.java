package com.springcloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author: song biao wei
 * @date: 2019/2/19 11:27
 * @description:
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "com.springcloud")
public class ConfigInfoProperties {
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
