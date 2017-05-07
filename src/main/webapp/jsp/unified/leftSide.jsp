<%@page import="java.util.HashMap" %>
<%@page import="java.util.Collection" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="org.springframework.web.context.WebApplicationContext" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="org.springframework.util.CollectionUtils" %>
<%@page import="java.util.Map" %>
<%@ page import="com.util.CookieUtil" %>
<%@ page import="com.pojo.User" %>
<%@ page import="com.pojo.Course" %>
<%@ page import="com.dao.ICourseDao" %>
<%@ page import="com.service.IClassService" %>
<%@ page import="com.service.ICourseService" %>
<%@ page import="com.pojo.Classroom" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
    ICourseService courseService = wac.getBean(ICourseService.class);
    User user = CookieUtil.getCurrentUser(request);

%>

<ul id="main-menu" class="main-menu">
    <li id="home">
        <a href="<%=request.getContextPath() %>/mvc/home">
            <i class="fa-home"></i>
            <span class="title">首页</span>
        </a>
    </li>

    <%
    if(user.getIdentity()==1){
        List<Course> courseList = courseService.getCourseByStudentClassId(user.getClassId());
    %>
    <li id="account">
        <a href="#">
            <i class="fa-th"></i>
            <span class="title">账号管理</span>
        </a>
        <ul>
            <li id="changePass">
                <a href="/mvc/changePass">
                    <i class="fa-cube"></i>
                    <span class="title">修改密码</span>
                </a>
            </li>
        </ul>
    </li>
    <%
        for(Course course:courseList){
    %>
    <li id="course<%=course.getId()%>">
        <a href="#">
            <i class="fa-th"></i>
            <span class="title"><%=course.getName()%></span>
        </a>
        <ul>
            <li id="listCourse">
                <a href="<%=request.getContextPath() %>/mvc/exp/listView?courseId=<%=course.getId()%>">
                    <i class="fa-cube"></i>
                    <span class="title">查看实验</span>
                </a>
            </li>
        </ul>
    </li>

    <%
        }
    }
    %>


    <%
    if(user.getIdentity()==2){
        List<Course> courseList = courseService.getCourseByTeacherId(user.getId());
        for(Course course:courseList){
    %>
    <li id="course<%=course.getId()%>">
        <a href="#">
            <i class="fa-th"></i>
            <span class="title"><%=course.getName()%></span>
        </a>
        <ul>
            <%
            for(Classroom classroom:course.getClassroomList()){
            %>
            <li id="class<%=course.getId() %>">
                <a href="#">
                    <i class="fa-cube" style="width:14px;height:14px;"></i>
                    <span class="title"><%= classroom.getName() %></span>
                </a>
                <ul>
                    <li id="addTask<%=course.getId()%>">
                        <a href="/mvc/exp/addExp?courseId=<%=course.getId()%>&classId=<%=classroom.getId()%>">
                            <i class="fa-cube" style="width:14px;height:14px;"></i>
                            <span class="title">添加实验</span>
                        </a>
                    </li>
                    <li id="viewTask<%=course.getId()%>">
                        <a href="/mvc/exp/viewTask?courseId=<%=course.getId()%>&classId=<%=classroom.getId()%>">
                            <i class="fa-cube" style="width:14px;height:14px;"></i>
                            <span class="title">查看实验</span>
                        </a>
                    </li>
                </ul>
            </li>
            <%
            }
            %>
        </ul>
    </li>
    <%
        }
    }
    %>

    <%
        if(user.getIdentity()==3){
    %>
    <li id="user">
        <a href="#">
            <i class="fa-th"></i>
            <span class="title">用户管理</span>
        </a>
        <ul>
            <li id="addStudent">
                <a href="/mvc/admin/addStudent">
                    <i class="fa-cube"></i>
                    <span class="title">添加学生</span>
                </a>
            </li>
            <li id="addTeacher">
                <a href="/mvc/admin/addTeacher">
                    <i class="fa-cube"></i>
                    <span class="title">添加教师</span>
                </a>
            </li>
            <li id="resetPassword">
                <a href="/mvc/admin/resetPassword">
                    <i class="fa-cube"></i>
                    <span class="title">重置密码</span>
                </a>
            </li>
        </ul>
    </li>

    <li id="class">
        <a href="#">
            <i class="fa-th"></i>
            <span class="title">班级管理</span>
        </a>
        <ul>
            <li id="addClass">
                <a href="/mvc/admin/addClass">
                    <i class="fa-cube"></i>
                    <span class="title">添加班级</span>
                </a>
            </li>
        </ul>
    </li>


    <li id="courseAdmin">
        <a href="#">
            <i class="fa-th"></i>
            <span class="title">课程管理</span>
        </a>
        <ul>
            <li id="addCourse">
                <a href="/mvc/admin/addCourse">
                    <i class="fa-cube"></i>
                    <span class="title">添加课程</span>
                </a>
            </li>
            <li id="allocation">
                <a href="/mvc/admin/allocation">
                    <i class="fa-cube"></i>
                    <span class="title">教师课程分配</span>
                </a>
            </li>
        </ul>
    </li>
    <%
        }
    %>

</ul>

<script type="text/javascript">

    $('#${leaf}').addClass('active');

    <c:forEach items="${parentPages}" var="parentPage">
    $('#${parentPage}').addClass('active opened active');
    </c:forEach>
</script>