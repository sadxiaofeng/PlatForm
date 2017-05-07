jQuery(document).ready(function($) {
	
	//初始化RDD
	//initSelect2('rddSelect', '');
	
    //是否展示示例代码
    $('#showSampleCode').on('click', function() {
        var checked = document.getElementById('showSampleCode').checked;
        if(checked) {
             window.frames["editorFrame"].window.showSampleCode();
             $('#showSampleCode').attr("data-original-title", "显示用户代码");
        } else {
             window.frames["editorFrame"].window.showUserCode();
             $('#showSampleCode').attr("data-original-title", "显示示例代码");
        }
    });

	//提交脚本应用
	$("form#codeForm").validate({
        submitHandler: function(form) {
        	toastr.clear();
        	
        	var prjId = getUrlParameter("prjId");
        	if(isNullOrEmpty(prjId, true)) {
        		warn("找不到对应的项目");
        		return;
        	}
        	
        	var appId = $('#appId').val();
        	var appName = $('#appName').val();
        	var language = $('#language').val();
        	var version = $('#version').val();
        	var appVersionHome = $('#appVersionHome').val();
            var mainArgs = $.trim($('#mainClassArgs').val());
        	var srcCodes = window.frames["editorFrame"].window.getSrcCode();
        	var rddSelect = $('#rddSelect').val();
    		
    		if(isNullOrEmpty(srcCodes, true)) {
    			toastr.warning('请填写源代码！');
    			return false;
    		} else {
               srcCodes = srcCodes.replace(/%/g, "%25");
            }
    		
    		//确认提交
    		confirm('确认提交', '您确认要提交吗？', function() {
				//控制台日志面板状态
				$('#closeModalBtn').hide();
				$('#consoleLog').html('');
				$('#consolePanel').addClass('collapsed');
				$('#detailBtn').html('详情+');
						
				//还原进度条状态
				$('#submitTips').html('当前进度：0%');
				$('#sbmtProgreBar').attr("aria-valuenow", '0');
				$('#sbmtProgreBar').css("width", '0%');
				$('#sbmtProgreBar').html('0%');
    			$('#submit_progress_modal').modal('show');
    			
    			var ws = connectWebsock(websocket_url);  
                ws.onopen = function () {  
                	var jsonParam = JSON.stringify({
                		"prjId": prjId,
                		"appId": appId,
				        "appName": appName,
				        "language": language,
				        "version": version,
				        "appVersionHome": appVersionHome,
				        "mainArgs": mainArgs,
				        "script": srcCodes,
				        "rddSelect": rddSelect,
				        "jobNumber": $('#jobNumber').val(),
				        "userName": $('#userName').val(),
				        "deptId": $('#deptId').val(),
				        "deptName": $('#deptName').val(),
				        "type": 'SCRIPT_SUBMIT'
				    });
    				
                	ws.send(jsonParam);
                };  
                ws.onmessage = function (event) { 
                	var jsobObj = jQuery.parseJSON(event.data);
                	
					//控制台日志
                	var desc = jsobObj.msg;
                	$('#consoleLog').append(buildConsoleLogDiv(desc));
                	$('#consoleLog').scrollTop( $('#consoleLog')[0].scrollHeight );
                	
					//进度面板状态
                	if(jsobObj.success == 0) { //失败
						$('#closeModalBtn').show();
						$('#submitTips').html(desc);
						
						$('#detailBtn').html('详情--');
						$("#consolePanel").attr('class', 'stage-details');
                    	toastr.error(desc);
                	} else if(jsobObj.success == 1) { //正常
                    	$('#submitTips').html(desc);
                    	$('#sbmtProgreBar').attr("aria-valuenow", jsobObj.percent);
                    	$('#sbmtProgreBar').css("width", jsobObj.percent);
                    	$('#sbmtProgreBar').html(jsobObj.percent);
                	} else if(jsobObj.success == 2) { //成功完成
                		$('#submit_progress_modal').modal('hide');                    	
                    	toastr.success('源代码提交成功!');
                	}
                };  
                	
                ws.onclose = function (event) {  
					if($('#closeModalBtn').is(':hidden')) {
						$('#submit_progress_modal').modal('hide');
					}
                };  
    		});
        }
    });
});