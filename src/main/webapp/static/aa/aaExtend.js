
//	      <!-- Start Overrride -->
AjaxAnywhere.prototype.showLoadingMessage = function() {
	var img = document.getElementById("loadingImg");
	var overlay=document.getElementById("overlay");
	if (img == null) {
		img = document.createElement("img");
		document.body.appendChild(img);
		img.id = "loadingImg";
		img.src =path+"/static/images/loadingAnimation.gif";
		img.style.position = "absolute";
	}
	if(overlay==null){
		overlay=document.createElement("overlay");
		document.body.appendChild(overlay);
		overlay.id="overlay";
		overlay.style.position = "absolute";
	}
	overlay.style.top = "0px";
	overlay.style.left = "0px";
	//overlay.style.height = "100%";
	overlay.style.height = $(window).height()+'px';
	overlay.style.width = $(window).width()+'px';
	overlay.style.zIndex = "10000";
	overlay.style.backgroundColor = "#555";
	overlay.style.filter = "alpha(opacity=40)";
	overlay.style.opacity = "0.4";
	overlay.style.display = "";
	img.style.top = "45%";
	img.style.left = "45%";
	img.style.zIndex = "10001";
	img.style.display = "";
}
AjaxAnywhere.prototype.hideLoadingMessage = function() {
	var img = document.getElementById("loadingImg");
	var overlay=document.getElementById("overlay");
	if (img != null){
		img.style.display = "none";
	}
	if(overlay!=null){
		overlay.style.display = "none";
	}
}
//		 <!-- End Overrride -->

function doAjax(button, action) {
//	button.disabled = true;
	formName = button.form.name;
	ajaxAnywhere.formName = formName;
	ajaxAnywhere.getZonesToReload = function() {
		return formName;
	}
	var form = button.form;
	form.action = action;
	ajaxAnywhere.submitAJAX();
	return false;
}
/*
 * send params to server with form by post
 * @author chenzhenling
 * 2010-09-01
 */
function doAjaxPost(form, action, dataObj) {
	formName = form.name;
	ajaxAnywhere.formName = formName;
	ajaxAnywhere.getZonesToReload = function() {
		return formName;
	}
	form.action = action;
	var tmpArr=[];
	for(var p in dataObj){
		var hideInput = document.createElement("input");
		hideInput.type="hidden";
		hideInput.name= p;
		hideInput.value= dataObj[p];
		tmpArr.push(hideInput);
		form.appendChild(hideInput);
	}
	ajaxAnywhere.submitAJAX();
	for(var i=0; i < tmpArr.length; i++){
		form.removeChild(tmpArr[i]);
	}
	return false;
}

function doAjaxGet(zone, url) {
	ajaxAnywhere.getZonesToReload = function() {
		return zone;
	}
	ajaxAnywhere.getAJAX(url);
}
