<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<shiro:user>
<script type="text/javascript">
	$(function(){
		$("#header_submit_btn").click(function(){
			$.post("${ctx}/profile",$("#inputForm").serialize(),function(){
				$('#profileDialog').modal('hide')
			});
			$("#profileDialog").on("hidden", function() {
			    $(this).removeData("modal");
			});
		});
	});
</script>
</shiro:user>
<style>
	.logo{
		height:30px; float:left;
		line-height:30px; 
		display:inline; padding-left:50px;
		position: relative;
		color:#0088CC;
		font-family: Arial,Tahoma,Verdana;
	}
	.title{
		font-size: 35px;
		font-weight:bolder;
	}
	.subtitle{
		width:500px;
		font-size: 1.3em;
		position: absolute;
		bottom: -5px;
		left:160px;
	}
</style>
<div id="header" style="height:40px;padding-top:9px; background:#f4f4f4; border-bottom:2px #0088CC solid;">
	<div class="row navbar-static-top">
	 	<div class="logo">
		 	<div class="title">壹商圈</div>
		 	<div class="subtitle">Yi  Business Circles  System</div>
	 	</div>
	 	<shiro:user>
		<div class="span1 btn-group pull-right" style="padding-right:10px;">
			<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="icon-user"></i> <shiro:principal property="name"/>
				<span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<li><a href="${ctx}/profile"
					data-toggle="modal"
					data-target="#profileDialog"
					data-backdrop="static" >修改密码</a></li>
				<li><a href="${ctx}/logout">退出</a></li>
			</ul>
		</div>
		</shiro:user>
	</div>
</div>
<div id="profileDialog" class="modal hide fade" role="dialog" aria-labelledby="profileLabel">
	<div class="modal-header"  style="padding-top:0px;padding-bottom: 0px;">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h5>修改密码</h5>
	</div>
	<div class="modal-body" style="padding-top:20px;padding-bottom: 0">
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
		<a href="#" id="header_submit_btn" class="btn btn-primary">提交</a>
	</div>
</div>