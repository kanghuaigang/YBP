package com.asc.bdhub.entity.account;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.asc.bdhub.entity.IdEntity;
import com.google.common.collect.Lists;

@Entity
@Table(name="YSQ_ROLE")
public class Role  extends IdEntity{

	private String roleName;	//角色名称
	private String roleCode;	//角色编码
	private List<Menu> menus=Lists.newArrayList();
	/** 创建时间 */
	private Date createDate;
	private List<UserInfo> users=Lists.newArrayList();
	private boolean checked;
	private List<MenuPrivilege> mps=Lists.newArrayList();
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "ASC_ROLE_MENU", 
    joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }, 
    inverseJoinColumns = { @JoinColumn(name = "menu_id", referencedColumnName = "id") })
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToMany(mappedBy = "roles")
	public List<UserInfo> getUsers() {
		return users;
	}
	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}
	public void addMenu(Menu menu){
		this.getMenus().add(menu);
	}
	
	public void removeMenu(Menu menu){
		this.getMenus().remove(menu);
	}
	@Transient
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return roleName;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "ASC_ROLE_PRIVILEGE", 
    joinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, 
    inverseJoinColumns = { @JoinColumn(name = "MP_ID", referencedColumnName = "ID") })
	public List<MenuPrivilege> getMps() {
		return mps;
	}
	public void setMps(List<MenuPrivilege> mps) {
		this.mps = mps;
	}
	
	@Transient
	public List<Privilege> getPrivileges(){
		List<MenuPrivilege> mps=this.getMps();
		List<Privilege> pris=Lists.newArrayList();
		for(MenuPrivilege mp:mps){
			pris.add(mp.getPrivilege());
		}
		return pris;
	}
	
	public void addPrivilege(MenuPrivilege privilege){
		this.getMps().add(privilege);
	}
	
	public void addPrivileges(List<MenuPrivilege> privileges){
		this.getMps().addAll(privileges);
	}
	
	public void removePrivilege(MenuPrivilege privilege){
		this.getMps().remove(privilege);
	}
	
	
}
