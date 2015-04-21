var datePickerOptions={
	format:"yyyy-mm-dd",
	language: 'zh-CN',
	autoclose: 1,
	pickerPosition:"bottom-left",
	minView:2
};
function search(formName,url,page){
    if(formName && url && formName != '' && url != ''){
        var form = document.forms[formName];
	    var param={page:page};
	    doAjaxPost(form,url,param);
    }
    
}

Date.prototype.pattern = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份    
        "d+": this.getDate(), //日    
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时    
        "H+": this.getHours(), //小时    
        "m+": this.getMinutes(), //分    
        "s+": this.getSeconds(), //秒    
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度    
        "S": this.getMilliseconds() //毫秒    
    };
    var week = {
        "0": "\u65e5",
        "1": "\u4e00",
        "2": "\u4e8c",
        "3": "\u4e09",
        "4": "\u56db",
        "5": "\u4e94",
        "6": "\u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};
//把标签上data遍历，比对val中的id，输出text
function JsonChangeIdToText(divId,tagName){
	var tags = $('#'+divId).find(tagName);
	$.each(tags,function(index,element){
		var td = $(element);
		var val = td.attr('val');
		if(val!=null && val!=""){
			var data = eval(td.attr('data'));
			for(var i=0;i<data.length;i++){
				if(data[i].id==val){
					td.html(data[i].text);
					break;
				}
			}
		}
	});
}
//下拉消失时，输入的必须存在
function comboboxOnHidePanel(valueName,defValue){
	if(defValue==null) defValue = "";
	return " var value = $(this).combobox('getText');"+
				" var flag = false;"+
				"  $.each($(this).combobox('getData'),function(i,data){"+
				" 	   if(data."+valueName+"==value){"+
				" 		   flag = true;"+
				" 		   return;"+
				" 	   }"+
				"    });"+
				"    if(!flag){"+
				" 	   $(this).combobox('setValue','"+defValue+"');"+
				"   }";
}
BOSSDATA = {}
//实施阶段-阶段分类
BOSSDATA.stepEnumJson=[{
	   "id":"1",
	   "text":"商务沟通",
	   "items":[]
   },{
	   "id":"2",
	   "text":"安装配置",
	   "items":[]
   },{
	   "id":"3",
   		"text":"数据待传",
	   "items":[]
   },{
	   "id":"4",
   		"text":"数据验收",
	   "items":[{
		    "id":"1",
   			"text":"数据初验中"
	   			},{
   			"id":"2",
   			"text":"数据终验中"
   				},{
   			"id":"5",
   			"text":"驳回"
	   }]
   },{
	   "id":"5",
   		"text":"等待实施",
	   "items":[{
		    "id":"3",
   			"text":"经销商内部"
	   			},{
   			"id":"4",
   			"text":"不同意/不配合"
	   }]
   },{
	   "id":"6",
  	   "text":"实施完成",
	   "items":[]
   },{
	    "id":"7",
  	   "text":"取消采集",
	   "items":[]
   }];
//原因分类
BOSSDATA.reasonTypeJson=[{
		"id":"1",
		"text":"技术/系统问题"
	},{
		"id":"2",
		"text":"费用问题"
	},{
		"id":"3",
		"text":"无法联系"
	},{
		"id":"4",
		"text":"经销商内部"
	},{
		"id":"5",
		"text":"GSP认证"
	},{
		"id":"6",
		"text":"产品未经营"
	},{
		"id":"7",
		"text":"更换采集方式"
	},{
		"id":"8",
		"text":"数据上传"
	},{
		"id":"9",
		"text":"无合作关系"
	},{
		"id":"10",
		"text":"三方协议"
	},{	
		"id":"11",
		"text":"流向费用"
	},{
		"id":"12",
		"text":"其他"
}];
//跟进方
BOSSDATA.followJson=[{
		"id":"1",
		"text":"安捷力"
	},{
		"id":"2",
		"text":"厂商"
}];
//跟进方式，实施方式
BOSSDATA.followtypeJson=[{
		"id":"1",
		"text":"上门跟进"
	},{
		"id":"2",
		"text":"远程跟进"
}];
//当前滞留位置
BOSSDATA.curpostionJson=[{
		"id":"0",
		"text":"400处理中"
	},{
		"id":"1",
		"text":"主数据处理中"
	},{
		"id":"2",
		"text":"商务处理中"
	},{
		"id":"3",
		"text":"云端处理中"
	},{
		"id":"4",
		"text":"预处理处理中"
	},{
		"id":"5",
		"text":"质检处理中"
}];
//工单状态
BOSSDATA.taskStateJson=[{
	"id":"1",
	"text":"进行中"
},{
	"id":"9",
	"text":"完成"
},{
	"id":"10",
	"text":"关闭"
}];