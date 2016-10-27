package com.hys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hys.common.IdEntity;

/**
 * 系统用户
 * @author hys
 *
 */
@Entity  
@Table(name="sys_user") 
public class User extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4218760041602635765L;
	@Column(unique=true,length=200,nullable=false)
	private String username;
	
	@Column(length=200,nullable=false)
	private String password;
	
	@Column(length=2)
	private Integer enabled;//是否可用
	
	@Transient
	private List<Role> roles;//角色,缓存技术获取

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


	
}
