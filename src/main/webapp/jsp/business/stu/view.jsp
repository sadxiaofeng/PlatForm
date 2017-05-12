<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
<style>
    td{
        color:#000 !important;
        font-weight: 500 !important;
    }
</style>
<div>
    <table class="table table-model-2 table-hover table-condensed">
        <thead>
        <tr>
            <th>任务编号</th>
            <th>课程名</th>
            <th>出题老师</th>
            <th>标题</th>
            <th>创建时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>

    <tbody>
    <c:if test="${!empty submitList}">
        <c:forEach items="${submitList}" var="submit" varStatus="i">
            <tr>
                <td>${submit.experimentId}</td>
                <td>${submit.course.name}</td>
                <td>${submit.course.teacher.name}</td>
                <td>${submit.experiment.title}</td>
                <td>
                    <fmt:formatDate value="${submit.createTime}"
                                    pattern="yyyy-MM-dd HH:mm aa" type="date" dateStyle="long"/></td>
                <td>${{0:"未开始",
                        1:"未完成",
                        2:"完成未审批",
                        3:"已审批"
                }[submit.status]}</td>
                <td>
                    <a href="javascript:void(0);" class="editexp" id="${submit.id}"><span class="fa-pencil-square-o" title="查看同步表状态"></span> </a>
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
<input type="hidden" id="courseId" value="${courseId}">
<script>
    $(".editexp").click(function(){
        $id = $(this).attr("id");
        window.location.href="/mvc/exp/editExp?courseId="+$("#courseId").val()+"&expId="+$id;
    });
</script>