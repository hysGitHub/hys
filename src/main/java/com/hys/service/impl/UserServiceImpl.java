package com.hys.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.dao.UserDao;
import com.hys.dao.UserRoleDao;
import com.hys.entity.User;
import com.hys.entity.UserRole;
import com.hys.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	@Autowired
	UserRoleDao userRoleDao;

	public User getUserByName(String username) throws Exception {
		List<User> us = userDao.findByProperty("username", username);
		if(null == us) return null;
		if(us.size()==0 || us.size() >1)
			throw new Exception(username+"非法数据");
		return us.get(0);
	}

	@Override
	public void save(User t) {
		t.setEnabled(1);
		userDao.save(t);
		//角色为普通用户
		UserRole rl = new UserRole();
		rl.setUserId(t.getId());
		rl.setRole("2");
		userRoleDao.save(rl);
	}

	@Override
	public User update(User t) {
		userDao.update(t);
		return userDao.get(t.getId());
	}

	@Override
	public User saveFectch(User t){
		userDao.save(t);
		return userDao.get(t.getId());
	}

	@Override
	public List<User> list(User t) {
		return null;
	}

	@Override
	public void del(User t) {
		userDao.delete(t);
	}

	@Override
	public int validateLogin(User u) {
		// TODO Auto-generated method stub
		return 0;
	}
}
