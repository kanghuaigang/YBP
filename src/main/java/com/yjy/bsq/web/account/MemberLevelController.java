package com.yjy.bsq.web.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjy.bsq.entity.account.UserInfo;
import com.yjy.bsq.entity.member.MemberLevel;
import com.yjy.bsq.service.account.AccountService;

@Controller
@RequestMapping(value = "/memberLevel")
public class MemberLevelController {
	private static final int PAGE_SIZE = 10;
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="add",method = RequestMethod.POST)
	public String registerForm(Model model) {
		model.addAttribute("memberLevel", new MemberLevel());
		return "member/update";
	}
	
	@ResponseBody
	@RequestMapping(value="list",method = RequestMethod.POST)
	public Page<UserInfo> list(
			@RequestParam(value = "start") Integer start,
			@RequestParam(value = "memberLevel", required = false) MemberLevel memberLevel) {
		Page<UserInfo> userPages=accountService.getAllUser(start);
		return userPages;
	}
}
