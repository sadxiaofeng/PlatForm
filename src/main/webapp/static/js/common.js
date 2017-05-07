//初始化公共组件
toastr.options = {
    "closeButton": true, //是否显示关闭按钮
    "debug": false, //是否使用debug模式
    "positionClass": "toast-top-right",//弹出窗的位置
    "showDuration": "300",//显示的动画时间
    "hideDuration": "1000",//消失的动画时间
    "timeOut": "5000", //展现时间
    "extendedTimeOut": "1000",//加长展示时间
    "showEasing": "swing",//显示时的动画缓冲方式
    "hideEasing": "linear",//消失时的动画缓冲方式
    "showMethod": "fadeIn",//显示时的动画方式
    "hideMethod": "fadeOut" //消失时的动画方式
};

/**
 * 初始化表格
 * @param tableId
 */
function initDataTable(tableId) {
	$("#" + tableId).dataTable({
		"bPaginate": true,
	    "bLengthChange": true,
	    "bFilter": true,
	    "bSort": false,
	    "bInfo": true,
	    "bRetrieve": true,
	    "bDestroy":true,
	    "searching": true,
		"bStateSave": true, //保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了
	    "bAutoWidth": true,
		"sPaginationType": "full_numbers", //分页，一共两种样式，full_numbers和two_button(默认)
		"oLanguage": {
			  "sLengthMenu": "每页显示 _MENU_ 条记录",
			  "sZeroRecords": "查询不到任何相关数据",
			  "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			  "sInfoEmtpy": "找不到相关数据",
			  "sInfoFiltered": "数据表中共为 _MAX_ 条记录)",
			  "sProcessing": "正在加载中...",
			  "sSearch": "搜索",
			  "sUrl": "", //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
			  "oPaginate": {
				  "sFirst":    "第一页",
				  "sPrevious": " 上一页 ",
				  "sNext":     " 下一页 ",
				  "sLast":     " 最后一页 "
			  }
		  },
		"bJQueryUI": false, //可以添加 jqury的ui theme  需要添加css
	});
}

/**
 * 创建资源表格
 * @param tableId
 * @param datas
 */
function buildResourceTable(tableId, datas) {
	var bodyHtml = "";
	
	var len = datas.length;
	for(var i = 0; i < len; i++) {
		var data = datas[i];
		bodyHtml += "<tr>";
		bodyHtml += "<td>" + data.id + "</td>";
		bodyHtml += "<td>" + data.name + "</td>";
		
		bodyHtml += "<td>";
		bodyHtml += "<li style=\"cursor:pointer;\" onclick=\"editResource('" + data.id + "','" + data.name + "')\" class=\"fa fa-edit\" title=\"编辑\" />";
		bodyHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<li style=\"cursor:pointer;\" onclick=\"deleteResource('" + data.id + "')\" class=\"fa fa-trash\" title=\"删除\" />";
		bodyHtml += "</td>";
		
		bodyHtml += "</tr>";
	}
	
	$('#' + tableId).dataTable().fnClearTable(); 
	$('#' + tableId).dataTable().fnDestroy(); 
	
	$('#' + tableId + " tbody").append(bodyHtml);
	initDataTable(tableId);
}

/**
 * 创建角色表格
 * @param tableId
 * @param datas
 */
function buildRoleTable(tableId, datas) {
	var bodyHtml = "";
	
	var len = datas.length;
	for(var i = 0; i < len; i++) {
		var data = datas[i];
		bodyHtml += "<tr>";
		bodyHtml += "<td>" + data.id + "</td>";
		bodyHtml += "<td>" + data.name + "</td>";
		bodyHtml += "<td>" + data.weight + "</td>";
		bodyHtml += "<td>" + data.resNames + "</td>";
		
		bodyHtml += "<td>";
		
		if(!isNullOrEmpty(data.resIds, true)) {
			bodyHtml += "<li style=\"cursor:pointer;\" onclick=\"resetSelects();editRole('" + data.id + "','" + data.name + "','" + data.resIds + "','" + data.weight + "')\" class=\"fa fa-edit\" title=\"编辑\" />";
			bodyHtml += "&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		
		bodyHtml += "<li style=\"cursor:pointer;\" onclick=\"deleteRole('" + data.id + "')\" class=\"fa fa-trash\" title=\"删除\" />";
		
		bodyHtml += "</td>";
		
		bodyHtml += "</tr>";
	}
	
	$('#' + tableId).dataTable().fnClearTable(); 
	$('#' + tableId).dataTable().fnDestroy(); 
	
	$('#' + tableId + " tbody").append(bodyHtml);
	initDataTable(tableId);
}

/**
 * 创建用户表格
 * @param tableId
 * @param datas
 */
function buildUserTable(tableId, datas) {
	var bodyHtml = "";
	
	var len = datas.length;
	for(var i = 0; i < len; i++) {
		var data = datas[i];
		bodyHtml += "<tr>";
		bodyHtml += "<td>" + data.id + "</td>";
		bodyHtml += "<td>" + data.jobNumber + "</td>";
		bodyHtml += "<td>" + data.userName + "</td>";
		bodyHtml += "<td>" + data.dept + "</td>";
		bodyHtml += "<td>" + data.roleNames + "</td>";
		
		bodyHtml += "<td>";
		
		bodyHtml += "<li style=\"cursor:pointer;\" onclick=\"resetSelects();editUser('" + data.id + "','" + data.jobNumber + "','" + data.roleIds + "')\" class=\"fa fa-edit\" title=\"编辑\" />";
		bodyHtml += "&nbsp;&nbsp;&nbsp;&nbsp;";
		
		bodyHtml += "<li style=\"cursor:pointer;\" onclick=\"deleteUser('" + data.id +"','" + data.jobNumber + "')\" class=\"fa fa-trash\" title=\"删除\" />";
		
		bodyHtml += "</td>";
		
		bodyHtml += "</tr>";
	}
	
	$('#' + tableId).dataTable().fnClearTable(); 
	$('#' + tableId).dataTable().fnDestroy(); 
	
	$('#' + tableId + " tbody").append(bodyHtml);
	initDataTable(tableId);
}

/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format) {
	/*
	 * eg:format="yyyy-MM-dd hh:mm:ss";
	 */
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
		// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
						- RegExp.$1.length));
	}

	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1
							? o[k]
							: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}

/**
 * 判断对象是否为空
 * @param obj
 * @param isTrim
 * @returns {Boolean}
 */
function isNullOrEmpty(obj, isTrim) {
	if(isTrim) {
		if($.trim(obj) == '') {
			return true;
		}
	} else {
		if(obj == '') {
			return true;
		}
	}
	
	if(typeof(obj) == 'undefined') {
		return true;
	}
	
	if(obj == null) {
		return true;
	}
	
	return false;
}

/**
 * 请求数据
 * @param url
 * @param params
 * @param callback
 */
function request(url, params, callback) {
	$.ajax({
	  type: 'POST',
	  timeout : 30000,
	  url: url,
	  data: params,
	  success: callback,
	  dataType: 'json'
	});
}

/**
 * 验证时间
 */
function validateTime() {
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	
	if(isNullOrEmpty(startTime, true)) {
		alert("请选择开始时间");
		return false;
	}
	
	if(isNullOrEmpty(endTime, true)) {
		alert("请选择结束时间");
		return false;
	}
	
	var dat = new Date().format('yyyy-MM-dd') + " ";
	
	var st = new Date(dat + startTime);  
    var et = new Date(dat + endTime);  
	var internal = et - st;
	if(internal <= 0) {
		alert("结束时间必须大于开始时间！");
		return false;
	}
	
	return true;
}

/**
 * 获取URL参数
 * @param name
 * @returns
 */
function getUrlParameter(name){  
    //构造一个含有目标参数的正则表达式对象  
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");  
    //匹配目标参数  
    var r = window.location.search.substr(1).match(reg);  
    //返回参数值  
    
    if (r!=null) {
    	return unescape(r[2]);  
    }
    
    return "";  
}

/**
 * 创建websocket客户端
 * 
 * @param wsUrl
 */
function connectWebsock(wsUrl) {
//	var wsClient = new WebSocket(wsUrl); 
	var wsClient = new SockJS(wsUrl); 
	
    return wsClient;
}

/**
 * 确认对话框
 * @param title0
 * @param content0
 * @param func
 */
//function confirm(title0, content0, func) {
//	$.confirm({
//		title: title0,
//	    icon: 'fa-info-circle',
//	    content: '<br>&nbsp;&nbsp;' + content0,
//	    animationBounce: 2,
//	    confirmButton: '<h1>确认</h1>',
//	    cancelButton: '<h1>关闭</h1>',
//    	confirmButtonClass: 'btn-info',
//        cancelButtonClass: 'btn-danger',
//	    confirm: func,
//	    cancel: function(){
//	    },
//	});
//}

/**
 * 线程睡眠
 * @param milliseconds
 */
function sleep(milliseconds) {
	var start = new Date().getTime(); 
    while ((new Date().getTime() - start) < milliseconds){
    }
}

/**
 * 字符串转数组
 * @param stringObj
 * @returns {Array}
 */
function string2Array(stringObj) {
	stringObj = stringObj.replace(/\[([\w, ]*)\]/, "$1");
	if (stringObj.indexOf("[") == 0) {// if has chinese
		stringObj = stringObj.substring(1, stringObj.length - 1);
	}
	var arr = stringObj.split(",");
	var newArray = [];//new Array();
	for (var i = 0; i < arr.length; i++) {
		var arrOne = arr[i];
		newArray.push(arrOne);
	}
	return newArray;
}

/**
 * 初始化选择器
 * @param selectId
 * @param placeHolder
 */
function initSelect2(selectId, placeHolder) {
	$("#" + selectId).select2({
		placeholder: placeHolder,
		allowClear: true
	}).on('select2-open', function() {
		$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
	});
}

/**
 * 展示loading
 * @param areaId
 */
function showLoading(areaId) {
	jQuery('#' + areaId).traingoLoading();
}

/**
 * 隐藏loading
 * @param areaId
 */
function hideLoading(areaId) {
	jQuery('#' + areaId).hideLoading();
}

/**
 * 创建控制台日志div
 * @param log
 * @returns {String}
 */
function buildConsoleLogDiv(log) {
	if(isNullOrEmpty(log, true)) {
		return "<div></div>";
	}
	
	if(log.indexOf('当前状态') >= 0) {
		return "<div class='fg-11'>" + log + "</div>";
	} else if(log.indexOf('BUILD SUCCESS') >= 0) {
		return "<div class='fg-10'>" + log + "</div>";
	} else if(log.indexOf('BUILD FAILURE') >= 0 || log.indexOf('ERROR') >= 0) {
		return "<div class='fg-9'>" + log + "</div>";
	} else if(log.indexOf('WARNING') >= 0) {
		return "<div class='fg-12'>" + log + "</div>";
	} else {
		return "<div class=''>" + log + "</div>";
	}
}

function info(msg) {
	toastr.clear();
	toastr.info(msg);
}

function warn(msg) {
	toastr.clear();
	toastr.warning(msg);
}

function error(msg) {
	toastr.clear();
	toastr.error(msg);
}

function success(msg) {
	toastr.clear();
	toastr.success(msg);
}

function contains(array, item) {
	for(i = 0; i < array.length; i++) {
		if(array[i] == item) {
			return true;
		}
	}
	
	return false;
}
