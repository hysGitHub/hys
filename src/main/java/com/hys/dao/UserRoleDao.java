package com.hys.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hys.common.BaseDao;
import com.hys.entity.UserRole;

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
