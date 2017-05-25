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
                                <input type="text" class="form-control disabled" id="classId" value="${experiment.title}" disabled/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">
                                需求：
                            </label>
                            <div class="col-sm-8">
                                <textarea class="form-control" style="height:150px;" disabled>${experiment.content}</textarea>
                            </div>
                        </div>
                    </div>

                    <c:if test="${!empty submitList}">
                    <div class="panel-body">
                        <table class="table table-model-2 table-hover table-condensed">
                            <thead>
                            <tr>
                                <th>任务编号</th>
                                <th>学生</th>
                                <th>状态</th>
                                <th>评分</th>
                                <th>操作</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:if test="${!empty submitList}">
                                <c:forEach items="${submitList}" var="submit" varStatus="i">
                                    <tr>
                                        <td>${submit.experimentId}</td>
                                        <td>${submit.user.name}</td>
                                        <td>${{0:"未开始",
                                                1:"完成中",
                                                2:"完成未审批",
                                                3:"已批阅"
                                                }[submit.status]}</td>
                                        <td><c:if test="${empty submit.grade}">尚未评分</c:if>${submit.grade}</td>
                                        <td>
                                            <a href="javascript:void(0);" class="view" id="${submit.id}"><span class="fa-eye" title="查看代码"></span> </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            <c:if test="${empty submitList}">
                                <tr>
                                    <!-- 需要修改列数dx -->
                                    <td colspan="20" align="center" bgcolor="#EFF3F7" style="font-size:16px;font-weight:bold;">没有找到相应的记录</td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    </c:if>
                </div>

            </div>
        </div>
    </form>
</div>
<input type="hidden" id="courseId" value="${courseId}"/>
<script>
    $(".view").each(function(){
        $(this).click(function(){
            window.location.href="/mvc/exp/checkCode?courseId="+$("#courseId").val()+"&submitId="+$(this).attr("id");
        });
    });
</script>