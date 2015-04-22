package com.yjy.bsq.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yjy.bsq.entity.account.Privilege;

public interface PrivilegeDao extends JpaRepository<Privilege, String> , JpaSpecificationExecutor<Privilege> {
}
