package com.springcloud.controller;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: song biao wei
 * @date: 2019/1/29 13:22
 * @description:
 */
@RestController
public class TestController {

    @GetMapping("/add")
    public Integer add(Integer a, Integer b){
        return a + b;
    }

    @GetMapping("/a/add")
    public Integer aadd(Integer a, Integer b){
        return a + b;
    }

    @GetMapping("/sub")
    public Integer sub(Integer a, Integer b){
        return a - b;
    }

    @GetMapping("/mul")
    public String mul(Integer a, Integer b, HttpServletRequest request){
        System.out.println("进入client-a!");
        return  request.getServerPort() + " client-a-" + a * b;
    }

    @GetMapping("/div")
    public Integer div(Integer a, Integer b){
        return a / b;
    }
}
