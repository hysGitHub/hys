package com.didispace.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.didispace.common.BaseDao;
import com.didispace.entity.UserRole;

@Repository
public class UserRoleDao extends BaseDao<UserRole>{

	@Override
	public Class<UserRole> getEntityClass() {
		return UserRole.class;
	}

	public List<UserRole> getRoleByUserId(String userId){
		return execute.find("from UserRole rl where rl.userId=?", userId);
	}
}
