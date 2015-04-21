<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script language="javascript">
   var path='${ctx}';
</script>
<link href="${ctx}/static/jquery/ui1.9.2/css/jquery-ui-1.9.2.custom.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/2.1.1/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/2.1.1/css/bootstrap-responsive.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/2.1.1/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/2.1.1/css/docs.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.10.0/validate.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/styles/default.css" type="text/css" rel="stylesheet" />

<link type="text/css" href="${ctx}/static/plupload-2.0.0/js/jquery.plupload.queue/css/jquery.plupload.queue.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/static/select2/select2.css" rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/common.js"></script>
<script type="text/javascript" src="${ctx}/static/plupload-2.0.0/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plupload-2.0.0/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="${ctx}/static/plupload-2.0.0/js/i18n/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/select2/select2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/select2/select2_locale_zh-CN.js"></script>

<script src="${ctx}/static/js/echarts_all.js"></script><!--套用JS地图样式-->
<script src="${ctx}/static/baidu/baiduTemplate.js"></script><!--百度模板引擎-->
<script src="${ctx}/static/js/public_js.js"></script>

<script type="text/javascript">
	var path="${ctx}";
	$(function(){
		$("#nav li a").click(function(){
			$('.panel').remove();
			$('.window-shadow').remove();
			$('.window-mask').remove();
		
			doAjaxGet("context",$(this).attr("title"));
			$("#nav li").each(function(index,item){
				$(item).removeClass("active");
			});
			$(this).parent().addClass("active");
		});
		$(window).keydown(function(e){
			var e = e || event,
			keycode = e.which || e.keyCode;
			if (keycode==13) {
				return false;
			}
		});
	});
</script>
<style type="text/css">
	.accordion-heading{background: #f9f9f9;}
</style>
<sitemesh:head/>
</head>

<body>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
	<div class="container" style="margin-top: 20px; margin-bottom:30px; background:#fdfdfd;width:99%">
		<shiro:user>
		<div class="row-fluid">
		    <div class="span2">
		    	<div class="accordion bs-docs-sidenav" id="accordion1">
		    		<c:forEach items="${menuList}" var="menu">
		    		<c:if test="${menu.visible}">
	    			<div class="accordion-heading">
		    			 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#${menu.menuCode}">
		    			 ${menu.menuName}
		    			 </a>
	    			 </div>
	    			 <div id="${menu.menuCode}" class="accordion-body collapse in">
						<div class="accordion-inner">
							<ul id="nav" class="nav nav-list bs-docs-sidenav-sec" style="border:none;">
						    	<c:forEach items="${menu.children}" var="cMenu">
						    		<c:if test="${cMenu.visible}">
						    			<li>
							    			<a href="#" title="${ctx}${cMenu.menuUrl}">
							    			${cMenu.menuName}
							    			</a>
						    			</li>
						    		</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
					</c:if>
					</c:forEach>
	    		</div>
		    </div>
		    <div class="span10">
		    	<sitemesh:body/>
		    </div>
	    </div>
	    </shiro:user>
	    <shiro:guest>
	    	<sitemesh:body/>
	    </shiro:guest>
	</div>
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	<script>
		/**
		 * 解决在IE8下，js数组没有indexOf方法
		 */
		if (!Array.prototype.indexOf){
			Array.prototype.indexOf = function(elt ){
			    var len = this.length >>> 0;
			    var from = Number(arguments[1]) || 0;
			    from = (from < 0)
			         ? Math.ceil(from)
			         : Math.floor(from);
			    if (from < 0)
					from += len;
			    for (; from < len; from++){
					if (from in this && this[from] === elt)
						return from;
			    }
			    return -1;
			};
		}
	</script>
	<script src="${ctx}/static/jquery-validation/1.10.0/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.10.0/validate-methods.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.10.0/messages_bs_zh.js" type="text/javascript"></script>
	<script src="${ctx}/static/bootstrap/2.1.1/js/bootstrap.min.js" type="text/javascript"></script>
	<shiro:user>
	<script src="${ctx}/static/aa/aa.js" type="text/javascript"></script>
	<script src="${ctx}/static/aa/aaExtend.js" type="text/javascript"></script>
	<script src="${ctx}/static/bootstrap/2.1.1/js/bootstrap-typeahead.js" type="text/javascript"></script>
	<script src="${ctx}/static/bootstrap/2.1.1/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bootstrap/2.1.1/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
	<script src="${ctx}/static/bootstrap/2.1.1/js/jquery.enplaceholder.js" type="text/javascript"></script>
	<script>
		jQuery.fn.limit=function(){
			var self = $("th[limit]");
			self.each(function(){
				var objString = $(this).text();
				var objLength = $(this).text().length;
				var num = $(this).attr("limit");
				if(objLength > num){
					$(this).attr("title",objString);
					objString = $(this).text(objString.substring(0,num) + "...");
				}
			});
		}
	</script>
	</shiro:user>
</body>
</html>