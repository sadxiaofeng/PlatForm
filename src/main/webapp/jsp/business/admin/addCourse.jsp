
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
<div class="container-fluid form-box">
    <form method="post" class="form-horizontal validate">
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-head">
                        添加课程
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                课程名：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="className"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                教师工号：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="jobNumber"/>
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
<script>
    $("#form_submit").click(function(){
        var name = $("#className").val();
        var jobNumber = $("#jobNumber").val();
        if(!checkParam(name,"课程名不能为空"))return ;
        if(!checkParam(jobNumber,"教师工号不能为空")){
            if(IsNum(jobNumber)){
                toastr.clear();
                toastr.warning("工号请填写数字！");
                return ;
            }
            return ;
        }
        request("createCourse",{name:name,jobNumber:jobNumber},function(data){
            if(data.head.state=="success"){
                alert("添加成功！");
                window.location.href="/mvc/home";
            }else{
                if(data.head.code=404){
                    alert("教师不存在");
                    return ;
                }else{
                    alert("服务器错误！");
                    return ;
                }
            }
        });
    });
</script>