jQuery(document).ready(function($) {
	initRemoteUserSelect2('projectAdmin');
	initRemoteUserSelect2('projectDeveloper');
	initRemoteUserSelect2('projectGuest');
	initRemoteDeptSelect2('deptname');
    
	// 判断是否是更新项目成功之后跳过来的
	var prjId = sessionStorage.getItem("updatePrjId");
	if(!isNullOrEmpty(prjId, true)) {
		 success('项目更新成功');
		 sessionStorage.removeItem("updatePrjId");
	}
	
	//更新项目
    $("form#createPrjForm").validate({
        submitHandler: function(form) {
        	var prjId = $('#prjId').val();
        	
            var projectName = $(form).find('#proName').val();
            if(isNullOrEmpty(projectName, true)) {
            	warn('请填写项目名称！');
            	return;
            }

      		var projectDesc = $(form).find('#proDescribe').val();
      		if(isNullOrEmpty(projectDesc, true)) {
      			warn('请填写项目描述！');
            	return;
            }

            showLoading('mainContent');
            
            //请求参数
            var params = {
            		prjId:prjId,
            		proName: projectName,
                	proDescribe: projectDesc
            };

            request('../../prjManager/updatePrj', params, function(result) {
            	if(result.success) {
                	sessionStorage.setItem("updatePrjId", prjId); 
                	window.location.href = '../../project/edit/' + prjId;
                } else {
                    error(result.msg);
                    hideLoading('mainContent');
                }
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
		initSelection : function (element, callback) {   // 初始化时设置默认值
	        var data = [];
	        $(element.val().split(",")).each(function () {
	            data.push({id: this, text: this});
	        });
	        callback(data);
	    },
		ajax: {
			type: 'POST',
			timeout : 30000,
			quietMillis: 10,
			url: '../../searchUserInfo',
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
		initSelection : function (element, callback) {   // 初始化时设置默认值
	        var data = [];
	        $(element.val().split(",")).each(function () {
	            data.push({id: this, text: this});
	        });
	        callback(data);
	    },
		ajax: {
			type: 'POST',
			timeout : 30000,
			quietMillis: 10,
			url: '../../searchDeptInfo',
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
