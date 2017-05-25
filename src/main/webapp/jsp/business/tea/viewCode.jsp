<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
<div class="container-fluid form-box">
    <form method="post" class="form-horizontal validate">
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-head">
                        实验任务
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                标题：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control disabled" id="classId" value="${submit.experiment.title}" disabled/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                需求：
                            </label>
                            <div class="col-sm-8">
                                <textarea class="form-control" style="height:150px;" disabled>${submit.experiment.content}</textarea>
                            </div>
                        </div>

                        <pre>${submit.content}</pre>
                    </div>

                    <div class="form-group text-center">
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-primary" id="run">
                                <i class="icon-ok icon-white"></i> 运行
                            </button>
                            &nbsp;&nbsp;
                            <c:if test="${empty submit.grade}">
                            <button type="button" class="btn" onclick="javascript:void(0);" id="grade">
                                评分
                            </button>
                            &nbsp;&nbsp;
                            </c:if>
                            <button type="button" class="btn" onclick="sessionStorage.clear();history.go(-1);"
                                    id="return">
                                返回
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </form>
</div>
<input type="hidden" id="submitId" value="${submit.id}">
<script>
    $("#run").click(function(){
        $("#coding_running").modal("show");
        request("/mvc/api/runningCode",{id:$("#submitId").val()},function(data){
            if(data.head.state="success"){
                $("#coding_running .modal-body").html(data.body);
            }
        });
    });

    $("#grade").click(function(){
        $("#grade_modal").modal("show");
    });

    $("#grade_submit").click(function(){
        var number = $("#grade_input").val();
        request("/mvc/exp/setGrade",{id:$("#submitId").val(),grade:number},function(data){
            if(data.head.state=='success'){
                alert("评分成功");
                window.location.history.go(-1);
            }
        });
    });

</script>