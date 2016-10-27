package com.hys.ba.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hys.common.RestResponse;
import com.hys.entity.Role;
import com.hys.service.RoleService;
/**
 * 角色添加
 * @author hys
 *
 */
@RestController
@RequestMapping("/ba/roles")
public class RoleBackAuthorRest {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET},value="/save")
	public RestResponse<Boolean> save(Role r){
		RestResponse<Boolean> res = new RestResponse<Boolean>();
		try{
			roleService.save(r);
			res.setResult(true);
		}catch(Exception e){
			res.setResult(false);
		}
		return res;
	}
	
	@RequestMapping(method={RequestMethod.GET},value="/list")
	public RestResponse<List<Role>> list(Role r){
		RestResponse<List<Role>> res = new RestResponse<List<Role>>();
		try{
			List<Role> list = roleService.list(r);
			res.setResult(list);
		}catch(Exception e){
			res.setResult(new ArrayList<Role>());
		}
		return res;
	}
}
