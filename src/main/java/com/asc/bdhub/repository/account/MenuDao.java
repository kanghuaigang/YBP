package com.asc.bdhub.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.asc.bdhub.entity.account.Menu;

public interface MenuDao extends JpaRepository<Menu, String>,JpaSpecificationExecutor<Menu> {
	List<Menu> findByIdIn(List<String> ids);
	Menu findByMenuCode(String menuCode);
}
