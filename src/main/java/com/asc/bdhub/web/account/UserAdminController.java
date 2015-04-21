package com.asc.bdhub.web.account;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asc.bdhub.entity.account.Menu;
import com.asc.bdhub.entity.account.Role;
import com.asc.bdhub.entity.account.UserInfo;
import com.asc.bdhub.service.account.AccountService;
import com.asc.bdhub.service.account.MenuService;
import com.asc.bdhub.service.account.RoleService;
import com.asc.bdhub.service.account.ShiroDbRealm.ShiroUser;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/admin")
public class UserAdminController {
	private static final int PAGE_SIZE = 10;
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(Model model) {
		List<Menu> menuList=menuService.getParentMenu();
		Subject currentUser = SecurityUtils.getSubject();
		ShiroUser user = (ShiroUser) currentUser.getPrincipal();
		for(Menu menu:menuList){
			List<Menu> cMenu=menuService.getChildrenMenu(menu.getId());
			for (Menu menu2 : cMenu) {
				if(currentUser.isPermitted(menu2.getMenuName())||user.loginName.equals("admin")){
					menu2.setVisible(true);
				}else{
					menu2.setVisible(false);
				}
			}
			
			menu.setChildren(cMenu);
			if(currentUser.isPermitted(menu.getMenuName())||user.loginName.equals("admin")){
				menu.setVisible(true);
			}else{
				menu.setVisible(false);
			}
		}
		model.addAttribute("menuList", menuList);
		return "index";
	}
	
	@RequestMapping(value="user",method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model) {
		Page<UserInfo> users = accountService.getAllUser(1);
		model.addAttribute("users", users);
		return "account/adminUserList";
	}
	
	@RequestMapping(value="user/search",method = RequestMethod.POST)
	public String search(@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model){
		UserInfo user=new UserInfo();
		user.setUserName(userName);
		user.setLoginName(loginName);
		Page<UserInfo> users = accountService.getAllUser(1);
		model.addAttribute("users", users);
		return "account/adminUserList";
	}

	@RequestMapping(value = "user/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", accountService.getUser(id));
		return "account/adminUserForm";
	}

	@RequestMapping(value = "user/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadUser") UserInfo user, RedirectAttributes redirectAttributes,Model model) {
		accountService.updateUser(user);
		redirectAttributes.addFlashAttribute("message", "更新用户" + user.getLoginName() + "成功");
		return list(1,model);
	}

	@RequestMapping(value = "user/delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes,Model model) {
		UserInfo user = accountService.getUser(id);
		accountService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", "删除用户" + user.getLoginName() + "成功");
		return list(1,model);
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preloadUser")
	public UserInfo getUser(@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			return accountService.getUser(id);
		}
		return null;
	}
	@RequestMapping(value="user/addRole/{id}",method = RequestMethod.GET)
	public String addRole(@PathVariable("id") Integer id, Model model) {
		UserInfo user=accountService.getUser(id);
		List<Role> userRoles=new ArrayList<Role>(user.getRoles());
		List<Role> roles=roleService.getAllRole(1, 1000).getContent();
		for (Role role : userRoles) {
			if(roles.contains(role)){
				role.setChecked(true);
			}
		}
		model.addAttribute("roles",roles);
		model.addAttribute("userId",id);
		return "account/userRole";
	}
	@RequestMapping(value="user/addRole",method = RequestMethod.POST)
	public String addRole(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "roleList") List<String> roleList,Model model) {
		accountService.addUserRoles(userId, roleList);
		return list(1,model);
	}
//	@RequestMapping(value="user/perm/{id}",method = RequestMethod.GET)
//	public String addDataPerm(@PathVariable("id") Integer id, Model model){
//		List<DataPermission> perms = initDataPerms();
//		UserInfo user = accountService.getUser(id);
//		List<DataPermission> userPerms = user.getPermissions();
//		for (DataPermission perm : perms) {
//			if(userPerms.contains(perm)){
//				perm.setChecked(true);
//			}
//		}
//		model.addAttribute("userId", id);
//		model.addAttribute("permissions", perms);
//		return "account/userDataPerm";
//	}
	
//	@RequestMapping(value="user/perm",method = RequestMethod.POST)
//	public String addDataPerm(@RequestParam(value = "userId") Integer userId,
//			@RequestParam(value = "permissions",required = false) List<String> permissions,Model model){
//		UserInfo user = accountService.getUser(userId);
//		if(permissions==null){
//			permissions=Lists.newArrayList();
//		}
//		user.getPermissions().clear();
//		for (String region : permissions) {
//			DataPermission perm = new DataPermission(region);
//			user.addPermission(perm);
//		}
//		accountService.updateUser(user);
//		return list(1,model);
//	}
//	
//	private List<DataPermission> initDataPerms(){
//		List<DataPermission> list = Lists.newArrayList();
//		for(String region:regionService.getRegion()){
//			list.add(new DataPermission(region));
//		}
//		return list;
//	}
}
