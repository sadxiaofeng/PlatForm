<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>

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
                            <i class=""></i>
                            <div class="block">
                                <label class="control-label col-sm-2">
                                    学号：
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="stuNumber"/>
                                </div>
                                <label class="control-label col-sm-1">
                                    姓名：
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="name"/>
                                </div>
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
    $("#identity").change(function(){

    });
</script>