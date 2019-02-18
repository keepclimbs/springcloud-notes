package com.springcloud.service;

import java.util.List;

/**
 * @author: song biao wei
 * @date: 2019/2/18 17:22
 * @description:
 */
public interface IUserService {
    /**
     * 使用feign 获取配置文件中用户
     */
    String getDefaultUser();

    /**
     * 使用feign 获取上下文中用户信息
     */
    String getContextUserId();

    /**
     * 使用restTemplate 调用服务
     */
    List<String> getProviderData();
}
