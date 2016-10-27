package com.hys.na.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hys.common.RestResponse;
import com.hys.common.RestResponseStatus;
import com.hys.entity.User;
import com.hys.service.UserService;


@RestController
@RequestMapping("/na/users")
public class UserNoAuthorRest{
	@Autowired
	UserService userService;
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET},value="/register")
	public RestResponse<Boolean> save(User u){
		RestResponse<Boolean> res = new RestResponse<Boolean>();
		try{
			userService.save(u);
			res.setResult(true);
		}catch(Exception e){
			res.setResult(false);
			res.setMessage(e.getMessage());
			res.setStatus(RestResponseStatus.INTERNAL_SERVER_ERROR.code());
		}
		return res;
	}
	
	@RequestMapping(method={RequestMethod.POST},value="/login")
	public RestResponse<Boolean> login(User u){
		RestResponse<Boolean> res = new RestResponse<Boolean>();
		if(null == u){
			res.setMessage("登录名称不能为空");
			res.setStatus(RestResponseStatus.BAD_REQUEST.code());
		}
		if(StringUtils.isBlank(u.getUsername())){
			res.setMessage("登录名称不能为空");
			res.setStatus(RestResponseStatus.BAD_REQUEST.code());
		}
		if(StringUtils.isBlank(u.getPassword())){
			res.setMessage("登录密码不能为空");
			res.setStatus(RestResponseStatus.BAD_REQUEST.code());
		}
		try{
			//判断用户是否存在
			int flag = userService.validateLogin(u);
			res.setResult(true);
		}catch(Exception e){
			res.setResult(false);
			res.setMessage(e.getMessage());
			res.setStatus(RestResponseStatus.INTERNAL_SERVER_ERROR.code());
		}
		return res;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/")
	public String root(){
		return "user info";
	}
	
}
