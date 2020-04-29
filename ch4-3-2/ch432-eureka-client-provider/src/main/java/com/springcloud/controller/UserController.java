package com.springcloud.controller;

import com.springcloud.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(User user , HttpServletRequest request){
		String token=request.getHeader("oauthToken");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			System.out.println(headerNames.nextElement());
		}
		System.out.println(token);
		return "hello,"+ user.getName()+ " : " + request.getServerPort();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser( @RequestBody User user){
		return "hello,"+user.getName();
	}


}
