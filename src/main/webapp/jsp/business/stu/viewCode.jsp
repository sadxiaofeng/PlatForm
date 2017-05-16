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
                </div>

            </div>
        </div>
    </form>
</div>