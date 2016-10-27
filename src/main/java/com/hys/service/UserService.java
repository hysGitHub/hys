package com.hys.service;


import com.hys.common.BaseService;
import com.hys.entity.User;

public interface UserService extends BaseService<User>{

	int validateLogin(User u);

	
}
