<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>登录页</title>
	<style type="text/css">
		.well{
			max-width: 300px; 
			margin: 0 auto;
		}
		#loginForm{
			margin: 0px;
			padding: 0px;
		}
		.alert{
			width: 280px; 
			margin: 0 auto 10px;
		}
	</style>
	<link href="${ctx}/static/bootstrap/2.1.1/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
</head>

<body>
	<%
	String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if(error != null){
	%>
		<div class="alert alert-error input-medium controls">
			<button class="close" data-dismiss="alert">×</button>
			<%out.print(error);%>
		</div>
	<%
	}
	%>
	<div class="well">
		<form id="loginForm"action="${ctx}/login" method="post" class="form-vertical">
			<div class="controls">
				<input type="text" id="username" name="username" value="${username}" class="input-block-level required" placeholder="用户名">
			</div>
			<div>
				<input type="password" id="password" name="password" class="input-block-level required" placeholder="密码">
			</div>
			<input id="submit_btn" class="btn btn-large btn-primary btn-block" type="submit" value="登录"/>
		</form>
	</div>
</body>
</html>
