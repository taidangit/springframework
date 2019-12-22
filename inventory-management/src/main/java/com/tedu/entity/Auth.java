package com.tedu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="auth")
public class Auth {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="auth_id")
	private Integer authId;
	
	@Column(name="permission")
	private int permission;
	
	@Column(name="active_flag")
	private int activeFlag;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="update_date")
	private Date updateDate;

	@OneToOne
	@JoinColumn(name="menu_id")
	private Menu menu;
	
	@OneToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	public Auth() {
	}

	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public int getPermission() {
		return this.permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public int getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
