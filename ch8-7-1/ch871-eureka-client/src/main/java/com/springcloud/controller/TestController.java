package com.springcloud.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Api(description = "测试源服务API接口")
@RestController
public class TestController {

	@ApiOperation(value = "加法", notes = "加法")
	@GetMapping("/add")
	public String add(Integer a, Integer b, HttpServletRequest request){
		// 验证给请求头header增加 属性
		System.out.println(request.getParameter("weight"));
		String requestHeader = request.getHeader("result");
		return "requestHeader: " + requestHeader + "" + (a + b);
	}
	
	@ApiOperation(value = "减法", notes = "减法")
	@GetMapping("/sub")
	public Integer sub(Integer a, Integer b){
		return a - b;
	}
	
	@ApiOperation(value = "乘法", notes = "乘法")
	@GetMapping("/mul")
	public Integer mul(Integer a, Integer b){
		return a * b;
	}
	
	@ApiOperation(value = "除法", notes = "除法")
	@GetMapping("/div")
	public Integer div(Integer a, Integer b){
		return a / b;
	}
	
	@PostMapping("/modifyRequestEntity")
	public String modifyRequestEntity (@RequestBody DemoVo demoVo) {
        return demoVo.toString();
	}
}
