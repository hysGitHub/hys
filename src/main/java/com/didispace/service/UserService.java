package com.didispace.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didispace.dao.UserDao;
import com.didispace.entity.User;

@Service("userService")
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;
	
	public void save(User u){
		u.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		u.setEnabled(1);
		userDao.save(u);
	}
	
	public List<User> list(){
		return userDao.findByCriteria(null);
	}

	public User getUserByName(String username) throws Exception {
		List<User> us = userDao.findByProperty("username", username);
		if(null == us) return null;
		if(us.size()==0 || us.size() >1)
			throw new Exception(username+"非法数据");
		return us.get(0);
	}
}
