package com.asc.bdhub.entity.account;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.asc.bdhub.entity.IdEntity;
import com.google.common.collect.Lists;

/**
 * 功能权限
 * 
 **/
@Entity
@Table(name = "YSQ_PRIVILEGE")
public class Privilege extends IdEntity{
	
	private String privilegeCode;		//权限编码
	private String privilegeName;		//权限名称
	private Date createDate=new Date();
	private List<MenuPrivilege> privileges=Lists.newArrayList();
	private boolean checked=false;
	
	public Privilege() {
	}

	public Privilege(Integer id) {
		this.id = id;
	}

	public String getPrivilegeCode() {
		return privilegeCode;
	}

	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},
			fetch=FetchType.LAZY,
			mappedBy="privilege")
	public List<MenuPrivilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<MenuPrivilege> privileges) {
		this.privileges = privileges;
	}

	@Transient
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Privilege) {  
			Privilege Privilege = (Privilege) obj;  
            return (id.equals(Privilege.id));  
        }  
        return super.equals(obj); 
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	

}