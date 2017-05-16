<%@ page import="com.pojo.User" %>
<%@ page import="com.util.CookieUtil" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    User user = CookieUtil.getCurrentUser(request);
%>
<style>
    .ms{
        min-width:220px;
        height:75.6px;
        line-height:75.6px !important;
    }
</style>
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
                <img src="<%=request.getContextPath() %>/static/plugins/xenon/assets/images/user-6.jpg" alt="user-image"
                     class="img-circle img-inline userpic-32" width="28"/>
                <span>
                    <%=user.getName() %>
                    <i class="fa-angle-down"></i>
                </span>
            </a>

            <ul class="dropdown-menu user-profile-menu list-unstyled" style="z-index:100;">
                <li>
                    <a>
                        <i class="fa-flag"></i>
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

    <ul class="user-info-menu right-links list-inline list-unstyled">
        <li class="user-profile ms" >
            <img src=""/>
        </li>
    </ul>

</nav>

<script type="text/javascript">

    $('#logoutBtn').on('click', function (ev) {
        ev.preventDefault();
        $("#logout_modal").modal('show');
    });

    websocket = new WebSocket("ws://localhost:8080/mvc/ws/login?id="+<%=user.getAccount()%>);
    websocket.onopen = function (evnt) {
        console.log("connection success");
    };
    websocket.onmessage = function (evnt) {
    };
    websocket.onerror = function (evnt) {
    };
    websocket.onclose = function (evnt) {
    }
//    if ('WebSocket' in window) {
//        websocket = new WebSocket("ws://localhost:8080/Origami/webSocketServer");
//    } else if ('MozWebSocket' in window) {
//        websocket = new MozWebSocket("ws://localhost:8080/Origami/webSocketServer");
//    } else {
//        websocket = new SockJS("http://localhost:8080/Origami/sockjs/webSocketServer");
//    }
</script>
	