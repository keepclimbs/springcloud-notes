package com.springcloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: song biao wei
 * @date: 2019/2/18 17:25
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "user")
public class UserConfig {
    private Integer id;
    private String realname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
