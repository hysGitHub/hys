package com.didispace.dao;

import org.springframework.stereotype.Repository;

import com.didispace.common.BaseDao;
import com.didispace.entity.User;

@Repository
public class UserDao extends BaseDao<User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
