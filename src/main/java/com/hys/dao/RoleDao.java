package com.hys.dao;

import org.springframework.stereotype.Repository;

import com.hys.common.BaseDao;
import com.hys.entity.Role;

@Repository
public class RoleDao extends BaseDao<Role> {

	@Override
	public Class<Role> getEntityClass() {
		return Role.class;
	}

}
