
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>

<div class="container-fluid form-box">
    <form method="post" class="form-horizontal validate">
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-head">
                        重置密码：
                    </div>
                    <div class="panel-body">

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                学号/工号信息：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="jobName"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                姓名：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="name"/>
                            </div>
                        </div>

                        <div class="form-group text-center">
                            <div class="col-sm-12">
                                <button type="button" class="btn btn-primary" id="form_submit">
                                    <i class="icon-ok icon-white"></i> 确认
                                </button>
                                &nbsp;&nbsp;
                                <button type="button" class="btn" onclick="sessionStorage.clear();history.go(-1);"
                                        id="return">
                                    返回
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="../../static/js/bootstrap.autocomplete.js"></script>
<script>
    $("#form_submit").click(function(){
        var jobName = $("#jobName").val();
        var name = $("#name").val();
        if(!checkParam(jobName,"学号/工号不能为空")) return ;
        if(!checkParam(jobName,"姓名不能为空")) return ;
        request("resetPwd",{name:name,jobName:jobName},function(data){
            if(data.head.state=='success'){
                alert("重置成功");
                window.location.reload("true");
            }else{
                if(data.head.code=400){
                    toastr.clear();
                    toastr.warning("学号/工号与姓名不匹配");
                }else{
                    toastr.clear();
                    toastr.warning("服务器错误");
                }
            }
        });
    });
</script>