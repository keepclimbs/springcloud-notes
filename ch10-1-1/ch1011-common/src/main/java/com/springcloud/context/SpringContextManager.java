package com.springcloud.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
* @author: song biao wei
* @date: 2019/2/2 11:41
* @description: spring上下文管理工具
*/
@Component
public class SpringContextManager implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextManager.applicationContext = applicationContext;
    }

    /**
     * 获取上下文
     *
     * @return Spring上下文
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取Spring配置
     * @param key 配置名称
     * @return 配置值
     */
    public static String getProperties(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }

    /**
     * 获取Spring配置<br>
     * 没有配置时，返回默认值
     * @param key 配置名称
     * @param defaultValue 默认值
     * @return 配置值
     */
    public static String getProperties(String key, String defaultValue) {
        return applicationContext.getEnvironment().getProperty(key, defaultValue);
    }
}


