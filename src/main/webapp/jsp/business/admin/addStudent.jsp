<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
<style>
    .block{
        position:relative;
    }
</style>
<div class="container-fluid form-box">
    <form method="post" class="form-horizontal validate">
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-head">
                        添加学生
                    </div>
                    <div class="panel-body">

                        <div class="form-group" id="select_acct">
                            <div class="block clearfix">
                                <label class="control-label col-sm-2">
                                    学号：
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control stuNumber" style="margin-bottom:3px;"/>
                                </div>
                                <label class="control-label col-sm-1">
                                    姓名：
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control name" style="margin-bottom:3px;"/>
                                </div>
                                <i class="fa-plus-square" style="color:black;position: absolute;right:140px;top:8px;"></i>
                            </div>

                        </div>

                        <div class="form-group" id="select_prof">
                            <label class="control-label col-sm-2">
                                班级：
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="classId"/>
                            </div>
                        </div>

                        <div class="form-group text-center">
                            <div class="col-sm-12">
                                <button type="button" class="btn btn-primary" id="form_submit">
                                    <i class="icon-ok icon-white"></i> 保存
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
        var checkFlag = true;
        var students = new Array();
        $(".block").each(function(i){
            if(!checkParam($(this).find(".stuNumber").val(),"请填写第"+(i+1)+"位学生学号")) {
                if(!IsNum($(this).find(".stuNumber").val())){
                    checkFlag = false;
                    toastr.clear();
                    toastr.warning("学号请填写数字！");
                    return ;
                }
                checkFlag = false;
                return;
            }
            if(!checkParam($(this).find(".name").val(),"请填写第"+(i+1)+"位学生姓名")) {
                checkFlag = false;
                return;
            }
            var obj = new Object();
            obj.id = $(this).find(".stuNumber").val();
            obj.name = $(this).find(".name").val();
            students[i] = obj;
        });
        if(!checkFlag) return;
        if(!checkParam($("#classId").val(),"请填写班级号")) {
            if(!IsNum($("#classId").val())){
                toastr.clear();
                toastr.warning("班级号请填写数字！");
                return ;
            }
            return ;
        }
        var param = {
            students:JSON.stringify(students),
            classId:$("#classId").val()
        }
        request("createStudent",param,function(data){
            if(data.head.state=="success"){
                alert("添加成功！");
                window.location.href="/mvc/home";
            }else{
                if(data.head.code=404){
                    alert("班级不存在");
                }else{
                    alert("服务器错误！");
                }
            }
        });
    });

    $(".fa-plus-square").click(function(){
        $target = $(this).parent().clone();
        $target.css({"display":"none"});
        $target.find("i").attr("class","fa-minus-square");
        $("#select_acct").append($target.prop("outerHTML"));
        $("#select_acct").find(".block:last").slideDown("normal");
    });

    $(document).on("click",".fa-minus-square",function(){
        $(this).parent().slideUp("normal",function(){
           $(this).remove();
        });
    });
</script>