package com.asc.bdhub.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asc.bdhub.entity.account.Privilege;
import com.asc.bdhub.repository.account.PrivilegeDao;

/**
 * 菜单管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional(readOnly = true)
public class PrivilegeService {
	
	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Transactional(readOnly = false)
	public Privilege addPrivilege(Privilege pri){
		return privilegeDao.save(pri);
	}
	
	public Page<Privilege> getAllPrivilege(int pageNum, int pageSize){
		PageRequest pageRequest=new PageRequest(pageNum-1, pageSize,Direction.DESC,"createDate");
		Page<Privilege> page=privilegeDao.findAll(pageRequest);
		return page;
	}
	
	
	@Transactional(readOnly = false)
	public void deleteOperate(String id){
		privilegeDao.delete(id);
	}
}
