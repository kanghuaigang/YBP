package com.asc.bdhub.web.account;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asc.bdhub.entity.account.Menu;
import com.asc.bdhub.entity.account.Privilege;
import com.asc.bdhub.service.account.MenuService;
import com.asc.bdhub.service.account.PrivilegeService;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	private static final int PAGE_SIZE = 15;
	@Autowired
	private MenuService menuService;
	@Autowired
	private PrivilegeService privilegeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model) {
		Page<Menu> menus = menuService.getAllMenu(pageNumber, PAGE_SIZE);
		model.addAttribute("menus", menus);
		return "account/menuList";
	}
	@RequestMapping(value="search",method = RequestMethod.POST)
	public String search(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model) {
		 return list(pageNumber,model);
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model){
		List<Menu> menus = menuService.getParentMenu();
		model.addAttribute("parentMenus", menus);
		return "account/addMenu";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String add(@Valid Menu menu, Model model){
		if(menu.getCreateDate()==null){
			menu.setCreateDate(new Date());
		}
		menuService.addMenu(menu);
		return list(1,model);
	}
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") String id, Model model) {
		model.addAttribute("menu", menuService.getMeun(id));
		return add(model);
	}
	
	@RequestMapping(value="delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id,Model model){
		try {
			menuService.deleteMenu(id);
		} catch (Exception e) {
			model.addAttribute("message", "删除菜单失败！");
		}
		return list(1,model);
	}
	
	@RequestMapping(value="addPrivilege/{id}",method = RequestMethod.GET)
	public String addPrivilege(@PathVariable("id") String id, Model model) {
		Menu menu=menuService.getMeun(id);
		List<Privilege> menuPrivilege=menu.getMenuPrivilege();
		List<Privilege> privileges=privilegeService.getAllPrivilege(1, 1000).getContent();
		for (Privilege privilege : menuPrivilege) {
			if(privileges.contains(privilege)){
				privilege.setChecked(true);
			}
		}
		model.addAttribute("privileges",privileges);
		model.addAttribute("menuId",id);
		return "account/menuPrivilege";
	}
	@RequestMapping(value="addPrivilege",method = RequestMethod.POST)
	public String addPrivilege(@RequestParam(value = "menuId") String menuId,@RequestParam(value = "privilegeList") List<String> privilegeList,Model model) {
		menuService.addMenuPrivilege(menuId, privilegeList);
		return list(1,model);
	}
	
	@RequestMapping(value = "checkMenuCode")
	@ResponseBody
	public String checkMenuCode(@RequestParam("menuCode") String menuCode) {
		if (menuService.checkMenuCode(menuCode) == null) {
			return "true";
		} else {
			return "false";
		}
	}
	
}
