<%@ page import="com.asc.state.DeptEnum"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="dept" value="<%=DeptEnum.values()%>"/>
<html>
<head>
	<title>用户注册</title>
	<link type="text/css" href="${ctx}/static/select2/select2.css" rel="stylesheet" />
	<style type="text/css">
	.select2-search{
		position: absolute;
		top:0px;
		left:-1000px;
	}
</style>
</head>

<body>
	<form id="addUserForm" name="addUserForm" action="${ctx}/register" method="post" class="form-horizontal">
		<fieldset>
			<div class="control-group">
				<label for="loginName" class="control-label">登录名:</label>
				<div class="controls">
					<input type="text" id="loginName" name="loginName" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" id="name" name="userName" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="plainPassword" name="plainPassword" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">确认密码:</label>
				<div class="controls">
					<input type="password" id="confirmPassword" name="confirmPassword" class="input-large required" equalTo="#plainPassword"/>
				</div>
			</div>
			<div class="control-group">
				<label for="tel" class="control-label">手机号码:</label>
				<div class="controls">
					<input type="text" id="tel" name="tel" class="input-large required isMobile"/>
				</div>
			</div>
			<div class="control-group">
				<label for="dept" class="control-label">部门:</label>
				<div class="controls">
					<form:select id="dept" path="user.ascDept.id"
					 items="${dept}" 
					 itemValue="code" 
					 itemLabel="name" 
					 cssClass="input-large"/>
				</div>
			</div>
			<div class="control-group">
				<label for="state" class="control-label">状态:</label>
				<div class="controls">
					<select id="state" class="form-control" name="state">
						<option value="1">启用</option>
						<option value="0">禁用</option>
					</select>
				</div>
			</div>
		</fieldset>
	</form>
	<script type="text/javascript" src="${ctx}/static/select2/select2.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/select2/select2_locale_zh-CN.js"></script>
	<script>
		var userFormValidator = $("#addUserForm").validate({
			rules: {
				loginName: {
					remote: "${ctx}/register/checkLoginName"
				}
			},
			messages: {
				loginName: {
					remote: "登录名已存在"
				}
			}
		});
		$("#loginName").focus();
		$('#state').select2({width:'225px'});
		$('#dept').select2({width:'225px'});
	</script>
</body>
</html>
