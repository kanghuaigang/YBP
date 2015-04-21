package com.asc.bdhub.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.asc.bdhub.entity.account.Privilege;

public interface PrivilegeDao extends JpaRepository<Privilege, String> , JpaSpecificationExecutor<Privilege> {
}
