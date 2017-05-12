<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>

<div class="container-fluid form-box">
    <form method="post" class="form-horizontal validate">
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-head">
                        修改密码：
                    </div>
                    <div class="panel-body">

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                原密码：
                            </label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="oldPwd"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                新密码：
                            </label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="pwd"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                确认密码：
                            </label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="pwdSec"/>
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
        var oldpwd = $("#oldPwd").val();
        var pwd = $("#pwd").val();
        var pwdSec = $("#pwdSec").val();
        if(!checkParam(oldpwd,"旧密码不能为空")) return ;
        if(!checkParam(pwd,"新密码不能为空")) return ;
        if(!checkParam(pwdSec,"确认密码不能为空")) return ;
        if(pwd!=pwdSec) {
            toastr.clear();
            toastr.warning("新旧密码不一样");
            return;
        }
        request("/mvc/checkPwd",{old:oldpwd,pwd:pwd},function(data){
            if(data.head.state=="success"){
                alert("修改成功！");
                window.location.href="/mvc/home";
            }else{
                if(data.head.code=500){
                    alert("旧密码不正确");
                    return ;
                }else{
                    alert("服务器错误");
                    return ;
                }
            }
        });
    });
</script>