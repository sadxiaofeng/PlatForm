<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
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
                <td>${submit.createTime}</td>
                <td>${{0:"未开始",
                        1:"未完成",
                        2:"完成未审批",
                        3:"已审批"
                }[submit.status]}</td>
                <td></td>
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