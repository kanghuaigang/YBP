package com.asc.bdhub.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.asc.bdhub.entity.account.Role;

public interface RoleDao extends JpaRepository<Role, String>,JpaSpecificationExecutor<Role> {
	Role findByRoleCode(String roleCode);
	Role findByRoleName(String roleName);
}
