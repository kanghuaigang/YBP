package com.asc.bdhub.entity.account;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.asc.bdhub.entity.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

/** 菜单 */
@Entity
@Table(name = "YSQ_MENU_PRIVILEGE")
public class MenuPrivilege extends IdEntity{
	private Menu menu;
	private Privilege privilege;
	private List<Role> roles=Lists.newArrayList();
	private boolean checked=false;

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="MENU_ID")
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="PRIVILEGE_ID")
	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "mps")
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Transient
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}