package com.hys.dao;

import org.springframework.stereotype.Repository;

import com.hys.common.BaseDao;
import com.hys.entity.User;

@Repository
public class UserDao extends BaseDao<User>{

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
