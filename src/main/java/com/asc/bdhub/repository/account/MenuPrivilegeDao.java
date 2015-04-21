package com.asc.bdhub.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.asc.bdhub.entity.account.MenuPrivilege;

public interface MenuPrivilegeDao extends JpaRepository<MenuPrivilege, String> , JpaSpecificationExecutor<MenuPrivilege> {
}
