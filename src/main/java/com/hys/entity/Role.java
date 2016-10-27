package com.hys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hys.common.IdEntity;

/**
 * 系统的角色
 * @author hys
 *
 */
@Entity  
@Table(name="sys_role") 
public class Role extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9170771754018781450L;
	@Column(length=200,unique=true,nullable=false)
	private String name;//角色名称
	@Column(name="describtion", length=500,unique=true,nullable=false)
	private String describtion;//功能描述
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	
}
