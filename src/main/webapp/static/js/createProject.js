jQuery(document).ready(function($) {
	initRemoteUserSelect2('projectAdmin');
	initRemoteUserSelect2('projectDeveloper');
	initRemoteUserSelect2('projectGuest');
	initRemoteDeptSelect2('deptname');
    
	//输入框内容变化
    $("#appName").focus(function(){
        $('#appName').css("border-color", "");
        $('#appName').attr('placeholder','');
    });

    $("#appVersion").focus(function(){
        $('#appVersion').css("border-color", "");
        $('#appVersion').attr('placeholder','');
    });
    
	// 判断是否是创建项目成功之后跳过来的
	var prjId = sessionStorage.getItem("prjId");
	if(!isNullOrEmpty(prjId, true)) {
		$('#createPrjForm').hide();
    	$('#createPrjSuccessTip').show();
    	success('项目创建成功');
    	
    	$('#returnAppList').attr('href', $('#returnAppList').attr('href') + prjId);
    	$("#createAppBtn").click(function(){
    		window.location.href="../mvc/subscribe/dt/addDataSubscription?projectId="+prjId;});
    	sessionStorage.removeItem("prjId");
	} else {
		$('#createPrjForm').show();
		$('#createPrjSuccessTip').hide();
	}
	
	//创建项目
    $("form#createPrjForm").validate({
        submitHandler: function(form) {
            var projectName = $(form).find('#projectName').val();
            if(isNullOrEmpty(projectName, true)) {
            	warn('请填写项目名称！');
            	return;
            }

      		var projectDesc = $(form).find('#projectDesc').val();
      		if(isNullOrEmpty(projectDesc, true)) {
      			warn('请填写项目描述！');
            	return;
            }
      		
      		var proGroup = $(form).find('#proGroup').val();
      		
            showLoading('mainContent');
            
            //请求参数
            var params = {
            	proName: projectName,
            	proGroup: proGroup,
            	proDescribe: projectDesc
            };

            request('../mvc/prjManager/createPrj', params, function(result) {
            	if(result.success) {
                	sessionStorage.setItem("prjId", result.projectId); 
                	window.location.href = '../mvc/createProject';
                } else {
                    error(result.msg);
                    hideLoading('mainContent');
                }
            });
        }
    });

    //提交应用
    $("form#createAppForm").validate({
        submitHandler: function(form) {
        	if(isNullOrEmpty(prjId, true)) {
        		warn("找不到对应的项目");
        		return;
        	}
        	
            var appName = $(form).find('#appName').val();
            if(isNullOrEmpty(appName, true)) {
            	$('#appName').val('');
                $('#appName').css("border-color", "#C77477");
                $('#appName').attr('placeholder','请输入应用名称');
                return;
            }

            var appVersion = $(form).find('#appVersion').val();
            if(isNullOrEmpty(appVersion, true)) {
            	$('#appVersion').val('');
                $('#appVersion').css("border-color", "#C77477");
                $('#appVersion').attr('placeholder','请输入版本号');
                return;
            }

            var codeType = $('input:radio[name="codeType"]:checked').val();
            var appDesc = $.trim($('#appDesc').val());
            
            $('#createApp_modal').modal('hide');
            showLoading('mainContent');
            
            //请求参数
            var params = {
        		prjId: prjId,
                appName: appName,
                version: appVersion,
                codeType: codeType,
                appDesc: appDesc
            };

            request('appManager/createApp', params, function(result) {
                if(result.success) {
                	sessionStorage.setItem("createPrjAppMsg", '应用创建成功'); 
                	window.location.href = $('#returnAppList').attr('href');
                } else {
                    toastr.error(result.msg);
                }

                hideLoading('mainContent');
            });
        }
    });
    
});

/**
 * 初始化用户Select2控件
 * @param id
 */
function initRemoteUserSelect2(id) {
	$("#" + id).select2({
		minimumInputLength: 2,
		placeholder: '输入用户名或者工号',
		formatInputTooShort: "请输入用户名或者工号",
        formatNoMatches: "没有匹配的用户或此用户已选择",
        formatSearching: "查询中...",
		allowClear: true,
		multiple: true,
		ajax: {
			type: 'POST',
			timeout : 30000,
			quietMillis: 10,
			url: 'searchUserInfo',
			data: function(term, page) {
				return {
					jobNumber: term
				};
			},
			dataType: 'json',
			results: function(data, page ) {
				return { results: data }
			}
		},
		formatResult: function(data) { 
			if(isNullOrEmpty(data, true) || isNullOrEmpty(data.name, true)) {
				return "<div class='select2-user-result' style='color:red'>不存在此用户</div>"; 
			}

			return "<div class='select2-user-result'>" + data.name + "</div>"; 
		},
		formatSelection: function(data) { 
			return  data.id; 
		}
	}).on('select2-open', function() {
		$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
	});
}

/**
 * 初始化部门Select2控件
 * @param id
 */
function initRemoteDeptSelect2(id) {
	$("#" + id).select2({
		minimumInputLength: 2,
		placeholder: '输入部门名称',
		formatInputTooShort: "请输入部门名称",
        formatNoMatches: "没有匹配的部门",
        formatSearching: "查询中...",
		allowClear: true,
		ajax: {
			type: 'POST',
			timeout : 30000,
			quietMillis: 10,
			url: 'searchDeptInfo',
			data: function(term, page) {
				return {
					dept: term
				};
			},
			dataType: 'json',
			results: function(data, page ) {
				return { results: data }
			}
		},
		formatResult: function(data) { 
			if(isNullOrEmpty(data, true) || isNullOrEmpty(data.name, true)) {
				return "<div class='select2-user-result' style='color:red'>不存在此部门</div>"; 
			}

			return "<div class='select2-user-result'>" + data.name + "</div>"; 
		},
		formatSelection: function(data) { 
			return  data.id; 
		}
	}).on('select2-open', function() {
		$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
	});
}

/**
 * 创建应用按钮
 */
function createAppAction() {
    $('#appName').val('');
    $('#appVersion').val('');
    $('#appDesc').val('');
    $('#createApp_modal').modal('show');
}

function createTask(projectId)
{
	alert(projectId);
}