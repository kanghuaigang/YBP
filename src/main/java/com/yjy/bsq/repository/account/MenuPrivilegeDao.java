package com.yjy.bsq.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yjy.bsq.entity.account.MenuPrivilege;

public interface MenuPrivilegeDao extends JpaRepository<MenuPrivilege, String> , JpaSpecificationExecutor<MenuPrivilege> {
}
