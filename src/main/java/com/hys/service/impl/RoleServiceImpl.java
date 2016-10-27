package com.hys.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.dao.RoleDao;
import com.hys.entity.Role;
import com.hys.service.RoleService;

@Service
@Transactional
class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleDao roleDao;

	@Override
	public void save(Role t) {
		roleDao.save(t);
	}

	@Override
	public Role update(Role t) {
		roleDao.update(t);
		return roleDao.get(t.getId());
	}

	@Override
	public Role saveFectch(Role t) {
		roleDao.save(t);
		return  roleDao.get(t.getId());
	}

	@Override
	public List<Role> list(Role t){
		DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
		if(t!=null){
			
		}
		return roleDao.findByCriteria(criteria );
	}

	@Override
	public void del(Role t) {
		 roleDao.delete(t);
	}

}
