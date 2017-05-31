<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>

<div class="container-fluid form-box">
    <form method="post" class="form-horizontal validate">
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-head">
                        为班级分配课程：
                    </div>
                    <div class="panel-body">

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                班级号：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="classId"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                课程号：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="courseId"/>
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
        var classId = $("#classId").val();
        var courseId = $("#courseId").val();
        if(!checkParam(classId,"班级号号不能为空"))return ;
        if(!checkParam(courseId,"课程号不能为空"))return ;
        request("/mvc/admin/allocationClass",{classId:classId,courseId:courseId},function(data){
            if(data.head.state=="success"){
                alert("分配课程成功！");
                window.location.href="/mvc/home";
            }else{
                if(data.head.code=101){
                    alert("班级不存在");
                }else if(data.head.code=102){
                    alert("课程不存在");
                }
            }
        });
    });
</script>