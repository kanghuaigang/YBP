package com.asc.bdhub.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.asc.bdhub.entity.account.UserInfo;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer>,JpaSpecificationExecutor<UserInfo> {
	UserInfo findByLoginName(String loginName);
}
