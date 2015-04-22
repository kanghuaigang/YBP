package com.yjy.bsq.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yjy.bsq.entity.account.Role;

public interface RoleDao extends JpaRepository<Role, String>,JpaSpecificationExecutor<Role> {
	Role findByRoleCode(String roleCode);
	Role findByRoleName(String roleName);
}
