<%@ page import="com.pojo.User" %>
<%@ page import="com.util.CookieUtil" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    User user = CookieUtil.getCurrentUser(request);
%>
<nav class="navbar user-info-navbar" role="navigation" style="margin-bottom: 10px;">

    <!-- Left links for user info navbar -->
    <ul class="user-info-menu left-links list-inline list-unstyled">

        <li class="hidden-sm hidden-xs">
            <a href="#" data-toggle="sidebar">
                <i class="fa-bars"></i>
            </a>
        </li>

        <li class="dropdown hover-line" style="min-width:220px;height:75.6px;">
            <a href="#" style="border-bottom:0px;">
                <div style="width:100%">
                    <div style="width:18%;float:left;">
                        <!--img src="<%=request.getContextPath() %>/static/img/spark.png" style="width:40px;height:21px;"-->
                    </div>
                    <div style="width:74%;float:right;line-height:20px;">
                        <span class="title" style="font-size:20px;color:black;">实验教学平台</span>
                    </div>
                </div>
            </a>
        </li>

    </ul>


    <!-- Right links for user info navbar -->
    <ul class="user-info-menu right-links list-inline list-unstyled">

        <li class="dropdown user-profile">
            <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                <img src="<%=request.getContextPath() %>/static/plugins/xenon/assets/images/user-4.png" alt="user-image"
                     class="img-circle img-inline userpic-32" width="28"/>
                <span>
                    <%=user.getName() %>
                    <%--<%=user.getUsername() %>--%>
                    <i class="fa-angle-down"></i>
                </span>
            </a>

            <ul class="dropdown-menu user-profile-menu list-unstyled" style="z-index:100;">
                <li>
                    <a>
                        <i class="fa-flag"></i>
                        <%--<%=user.getWorkId() %>--%>
                        <%=user.getAccount() %>
                    </a>
                </li>

                <li class="last">
                    <a id="logoutBtn" href="#">
                        <i class="fa-lock"></i>
                        退出
                    </a>
                </li>
            </ul>
        </li>
    </ul>

</nav>

<script type="text/javascript">

    $('#logoutBtn').on('click', function (ev) {
        ev.preventDefault();
        $("#logout_modal").modal('show');
    });


</script>
	