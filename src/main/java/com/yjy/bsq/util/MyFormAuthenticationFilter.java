package com.yjy.bsq.util;

import javax.servlet.ServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class MyFormAuthenticationFilter  extends FormAuthenticationFilter{
	
	@Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		String msg = ae.getMessage();
		if(msg!=null && msg.length()>=20){
			msg = msg.substring(0,20);
		}
		if(msg!=null && "Submitted credential".equals(msg)){
			msg = "用户名或密码错误！";
		}
		request.setAttribute(getFailureKeyAttribute(), msg);
    }
}
