<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>

<div class="container-fluid form-box">
    <form method="post" class="form-horizontal validate">
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-head">
                        添加教师：
                    </div>
                    <div class="panel-body">

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                工号：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="jobNumber"/>
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
<script>
    $("#form_submit").click(function(){
        var jobNumber = $("#jobNumber").val();
        var name = $("#name").val();
        if(!checkParam(jobNumber,"工号不能为空"))return ;
        if(!checkParam(name,"姓名不能为空"))return ;
        request("/mvc/admin/createTeacher",{jobNumber:jobNumber,name:name},function(data){
           if(data.head.state=="success"){
               alert("添加教师成功！");
               window.location.href="/mvc/home";
           }
        });
    });
</script>