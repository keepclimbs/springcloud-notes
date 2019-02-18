package com.springcloud.controller;

import com.springcloud.context.UserContextHolder;
import com.springcloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: song biao wei
 * @date: 2019/2/18 17:21
 * @description:
 */

@RestController
public class UserCtrl {
    @Autowired
    private IUserService userService;

    @GetMapping("/getDefaultUser")
    public String getDefaultUser(){
        return userService.getDefaultUser();
    }

    @GetMapping("/getContextUserId")
    public String getContextUserId(){
        return userService.getContextUserId();
    }

    @GetMapping("/getProviderData")
    public List<String> getProviderData() {
        return userService.getProviderData();
    }
}
