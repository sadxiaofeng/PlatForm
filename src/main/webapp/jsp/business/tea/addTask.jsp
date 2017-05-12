<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
<div class="container-fluid form-box">
    <form id="taskAdd"  method="post" class="form-horizontal validate" >
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                题目：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" id="title" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                要求：
                            </label>
                            <div class="col-sm-8">
                                <textarea class="form-control" rows="10" id="content"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                截止时间：
                            </label>
                            <div class="col-sm-8">
                                <input name="deadline" type="text" class="form-control" style="padding-left:5px;color:black;" id="deadline" onclick="SetDate(this,'yyyy-MM-dd hh:mm')" readonly="readonly" />
                            </div>
                        </div>
                    </div>


                </div>
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
    </form>
</div>
<input type="hidden" value="${courseId}" id="courseId"/>
<input type="hidden" value="${classId}" id="classId"/>
<script>
    $("#form_submit").click(function(){
        var param={
            title:$("#title").val(),
            content:$("#content").val(),
            deadline:$("#deadline").val(),
            courseId:$("#courseId").val(),
            classId:$("#classId").val()
        }
        request("/mvc/exp/createExp",param,function(data){
            if(data.head.status="success"){
                alert("实验任务创建成功！");
                window.location.href="/mvc/exp/viewTask?courseId="+$("#courseId").val()+"&classId="+$("#classId").val();
            }else{
                alert("未知错误，创建失败！")
            }
        });
    });
</script>