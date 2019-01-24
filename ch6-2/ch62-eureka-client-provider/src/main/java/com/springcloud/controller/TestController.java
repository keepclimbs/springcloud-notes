package com.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
	public String getUser(@RequestParam("username") String username, HttpServletRequest request) throws Exception {
		if(username.equals("spring")) {
			return "This is real user " + username + "server Port :" + request.getServerPort();
		}else {
			throw new Exception();
		}

	}
}
