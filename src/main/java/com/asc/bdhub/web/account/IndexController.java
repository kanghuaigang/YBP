package com.asc.bdhub.web.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asc.bdhub.entity.account.UserInfo;
import com.asc.bdhub.service.account.AccountService;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
	private static final int PAGE_SIZE = 10;
	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
	
	
	@RequestMapping(value="register",method = RequestMethod.POST)
	public String registerForm(Model model) {
		model.addAttribute("user", new UserInfo());
		return "account/register";
	}
	
	@ResponseBody
	@RequestMapping(value="list",method = RequestMethod.POST)
	public Page<UserInfo> list(@RequestParam(value = "start") Integer start) {
		Page<UserInfo> userPages=accountService.getAllUser(start);
		return userPages;
	}
}
