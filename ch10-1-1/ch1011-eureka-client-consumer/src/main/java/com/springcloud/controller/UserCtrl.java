package com.springcloud.controller;

import com.springcloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: song biao wei
 * @date: 2019/2/18 16:55
 * @description:
 */

@RestController
public class UserCtrl {
    @Autowired
    private IUserService userService;

    /**
     * 通过feign 访问 dataService 获取数据
     */
    @GetMapping("/getDefaultUser")
    public String getDefaultUser(){
        return userService.getDefaultUser();
    }

    /**
     * 获取上下文用户 如果header没有用户信息 就会报错
     */
    @GetMapping("/getContextUserId")
    public String getContextUserId(){
        return userService.getContextUserId();
    }

    /**
     * 通过 restTemplate 访问 dataService 获取数据
     */
    @GetMapping("/getProviderData")
    public List<String> getProviderData(){
        return userService.getProviderData();
    }
}
