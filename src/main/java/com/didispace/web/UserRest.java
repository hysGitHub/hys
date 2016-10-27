package com.didispace.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.entity.User;
import com.didispace.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRest{
	@Autowired
	UserService userService;
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET},value="/save")
	public String save(User u){
		userService.save(u);
		return "success";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/")
	public String root(){
		return "user info";
	}
	
}
