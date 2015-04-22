package com.yjy.bsq.web.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjy.bsq.entity.account.UserInfo;
import com.yjy.bsq.service.account.AccountService;

/**
 * 用户注册的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {
	private static final int PAGE_SIZE = 10;
	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String registerForm(Model model) {
		model.addAttribute("user", new UserInfo());
		return "account/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid UserInfo user,Model model) {
		accountService.registerUser(user);
		Page<UserInfo> users = accountService.getAllUser(1);
		model.addAttribute("users", users);
		return "account/adminUserList";
	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("loginName") String loginName) {
		if (accountService.findUserByLoginName(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}
}
