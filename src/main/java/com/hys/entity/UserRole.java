package com.hys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hys.common.IdEntity;

/**
 * 用户角色关系表
 * @author hys
 *
 */
@Entity  
@Table(name="sys_user_role") 
public class UserRole extends IdEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9014017547653312475L;
	@Column(name="user_id",nullable=false)
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="role_id",nullable=false)
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
