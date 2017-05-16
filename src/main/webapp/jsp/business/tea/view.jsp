<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
<style>
    table td{
        color:#000 !important;
        font-weight: 500 !important;
    }
</style>
<div>
    <table class="table table-model-2 table-hover table-condensed" id="">
        <thead>
        <tr>
            <th>任务编号</th>
            <th>标题</th>
            <th>创建时间</th>
            <th>截止时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <c:if test="${!empty experimentList}">
            <c:forEach items="${experimentList}" var="experiment" varStatus="i">
                <tr>
                    <td>${experiment.id}</td>
                    <td>${experiment.title}</td>
                    <td>${experiment.createTime}</td>
                    <td><fmt:formatDate value="${experiment.deadLine}" pattern="yyyy-MM-dd hh:mm"/></td>
                    <td>${{0:"未发布",
                            1:"已发布"
                            }[experiment.status]}</td>
                    <td>
                        <c:if test="${experiment.status == '0'}">
                            <a href="javascript:void(0);" id="${experiment.id }"
                               class="publish"><span class="fa-wrench" title="发布任务"></span> </a>
                            <a href="javascript:void(0);" id="${experiment.id }"
                               class="modify"><span class="fa-edit" title="修改"></span> </a>
                            <a href="javascript:void(0);" id="${experiment.id }"
                               class="delete"><span class="fa-remove" title="删除"></span> </a>
                        </c:if>
                        <a href="javascript:void(0);" id="${experiment.id }"
                           class="view"><span class="fa-eye" title="查看要求"></span> </a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

        <c:if test="${empty experimentList}">
            <tr>
                <!-- 需要修改列数dx -->
                <td colspan="20" align="center" bgcolor="#EFF3F7" style="font-size:16px;font-weight:bold;">没有找到相应的记录</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<input type="hidden" id="courseId" value="${courseId}"/>
<input type="hidden" id="classId" value="${classId}"/>

<script>
    $(".publish").each(function(){
        $(this).click(function() {
            var param = {
                id: $(this).attr("id"),
                classId: $("#classId").val()
            }
            request("/mvc/exp/publishExp", param, function (data) {
                if (data.head.status = "success") {
                    alert("发布成功！");
                    window.location.reload(true);
                }
            });
        });
    });
    $(".modify").each(function(){
        $(this).click(function() {
           window.location.href="/mvc/exp/addExp?courseId="+$("#courseId").val()+"&classId="+$("#classId").val()+"&expId="+$(this).attr("id");
        });
    });
    $(".delete").each(function(){
        $(this).click(function() {
            request("/mvc/exp/delete", {expId: $(this).attr("id")}, function (data) {
                if (data.head.state == "success") {
                    alert("删除成功！");
                    window.location.reload(true);
                }
            });
        });
    });
    $(".view").each(function(){
        $(this).click(function() {
            window.location.href = "/mvc/exp/showDetail?expId=" + $(this).attr("id") + "&courseId=" + $("#courseId").val();
        });
    });
</script>