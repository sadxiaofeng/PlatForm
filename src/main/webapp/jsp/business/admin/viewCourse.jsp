<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>

<div>
    <table class="table table-model-2 table-hover table-condensed">
        <thead>
        <tr>
            <th>编号</th>
            <th>课程名</th>
            <th>授课教师老师</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty courses}">
            <c:forEach items="${courses}" var="course" varStatus="i">
                <tr>
                <td>${course.id}</td>
                <td>${course.name}</td>
                <td>${course.teacher.name}</td>
            </tr>
            </c:forEach>
        </c:if>

        <c:if test="${empty courses}">
            <tr>
                <!-- 需要修改列数dx -->
                <td colspan="20" align="center" bgcolor="#EFF3F7" style="font-size:16px;font-weight:bold;">没有找到相应的记录</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
