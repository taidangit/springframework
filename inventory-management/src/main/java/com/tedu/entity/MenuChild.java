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
@Table(name="menu_child")
public class MenuChild {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menu_child_id")
	private int menuChildId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="url")
	private String url;
	
	@Column(name="id_menu")
	private String idMenu;
	
	@Column(name="order_index")
	private int orderIndex;
	
	@Column(name="active_flag")
	private int activeFlag;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="update_date")
	private Date updateDate;
	
	@OneToOne
	@JoinColumn(name="menu_id")
	private Menu menu;
	
	public int getMenuChildId() {
		return menuChildId;
	}
	public void setMenuChildId(int menuChildId) {
		this.menuChildId = menuChildId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(String idMenu) {
		this.idMenu = idMenu;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public int getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
