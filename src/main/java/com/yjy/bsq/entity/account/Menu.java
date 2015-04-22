package com.yjy.bsq.entity.account;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.yjy.bsq.entity.IdEntity;

/** 菜单 */
@Entity
@Table(name = "YSQ_MENU")
public class Menu extends IdEntity{
	/** 父菜单ID */
	private Integer parentId;
	/** 菜单编码 */
	private String menuCode;
	/** 菜单名称 */
	private String menuName;
	/** 菜单url */
	private String menuUrl;
	/** 菜单图标 */
	private String menuIcon;
	/** 是否显示 */
	private boolean visible;
	/** 是否选中 */
	private boolean checked;
	private boolean leaf;
	/** 角色列表 */
	private List<Role> roles=Lists.newArrayList();
	/** 创建时间 */
	private Date createDate;
	private Integer menuIndex;
	
	private List<Menu> children = Lists.newArrayList();

	@Column(length=100)
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Column(length=200)
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Column(length=500)
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	@Column(length=500)
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	@Transient
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Transient
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@JsonIgnore
	@ManyToMany(mappedBy = "menus")
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.S")  
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return menuName;
	}
	
	
	@Transient
	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	@Transient
	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public Integer getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(Integer menuIndex) {
		this.menuIndex = menuIndex;
	}
	
}