package com.asc.bdhub.service.account;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asc.bdhub.entity.account.Role;
import com.asc.bdhub.repository.account.MenuDao;
import com.asc.bdhub.repository.account.MenuPrivilegeDao;
import com.asc.bdhub.repository.account.RoleDao;

/**
 * 角色管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional(readOnly = true)
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private MenuPrivilegeDao menuPrivilegeDao;
	
	@Transactional(readOnly = false)
	public Role addRole(Role role){
		return roleDao.save(role);
	}
	
	public Page<Role> getAllRole(int pageNum, int pageSize){
		PageRequest pageRequest=new PageRequest(pageNum-1, pageSize,Sort.Direction.DESC,"createDate");
		Page<Role> page=roleDao.findAll(pageRequest);
		return page;
	}
	
	public Role getRole(String id){
		return roleDao.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void deleteRole(String id){
		roleDao.delete(id);
	}
	@Transactional(readOnly = false)
	public void addRoleMenus(String roleId,List<String> menuIds,List<String> privileges){
		Role role=this.getRole(roleId);
		role.getMenus().clear();
		role.getMps().clear();
		for(String menuId:menuIds){
			if(StringUtils.isNotBlank(menuId)){
				role.addMenu(menuDao.findOne(menuId));
			}
		}
		for(String pri:privileges){
			if(StringUtils.isNotBlank(pri)){
				role.addPrivilege(menuPrivilegeDao.findOne(pri));
			}
		}
		roleDao.save(role);
	}
	
	public Role getByFieldValue(String fieldName,String fieldValue){
		if(fieldName.equals("roleCode")){
			return roleDao.findByRoleCode(fieldValue);
		}else if(fieldName.equals("roleName")){
			return roleDao.findByRoleName(fieldValue);
		}else{
			return null;
		}
	}
}
