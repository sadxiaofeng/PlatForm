<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Admin Panel"/>
    <meta name="author" content="lisong"/>

    <title>实验教学平台</title>

    <%--<link rel="shortcut icon" href="<%=request.getContextPath() %>/static/img/favicon.ico"/>--%>

    <link rel="stylesheet"
          href="<%=request.getContextPath() %>/static/plugins/xenon/assets/css/fonts/linecons/css/linecons.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath() %>/static/plugins/xenon/assets/css/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/plugins/xenon/assets/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/plugins/xenon/assets/css/xenon-core.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/plugins/xenon/assets/css/xenon-forms.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/plugins/xenon/assets/css/xenon-components.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/plugins/xenon/assets/css/xenon-skins.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/plugins/xenon/assets/css/custom.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/toastr/toastr.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/confirm/jquery-confirm.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/mystyle.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/common.css">

    <script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/js/confirm/jquery-confirm.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/toastr/toastr.min.js"></script>

    <!-- loading -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/plugins/loading/css/loading.css">
    <script src="<%=request.getContextPath() %>/static/plugins/loading/js/jquery.traingoLoading.js"></script>

    <script src="<%=request.getContextPath() %>/static/js/websocket/sockjs-0.3.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/js/common.js"></script>

    <link type="text/css" href="<%=request.getContextPath() %>/static/css/tabs/jquery.pwstabs.min.css"
          rel="stylesheet"/>
    <link type="text/css" href="<%=request.getContextPath() %>/static/css/tabs/styles.css" rel="stylesheet"/>
    <script src="<%=request.getContextPath() %>/static/js/tabs/jquery.pwstabs-1.1.3.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        .loding{
            width:30px;
            height:30px;
            margin:5px auto;
            display: block;
        }
    </style>
</head>
<body class="page-body skin-navy">

<div class="modal fade" id="logout_modal" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title"><i class="fa-info-circle"></i>&nbsp;&nbsp;确认退出</h4>
            </div>

            <div class="modal-body">
                你确定要退出吗？
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-danger"
                        onclick="window.location.href='<%=request.getContextPath()%>/mvc/auth/loginout'">确定
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal" id="exp_alert">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">实验任务提醒</h4>
            </div>
            <div class="modal-body">
            </div>
        </div>
    </div>
</div>

<div class="modal" id="coding_running">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">代码运行结果</h4>
            </div>
            <div class="modal-body">
                <img src='/static/img/loading.gif' class='loding'/>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="grade_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">评分</h4>
            </div>
            <div class="modal-body">
                <form method="post" class="form-horizontal validate">
                    <div class="form-group">
                        <label class="control-label col-sm-3">
                            请评分：
                        </label>
                        <div class="col-sm-8">
                            <input class="form-control" id="grade_input" type="text"/>
                        </div>
                    </div>
                    <div class="col-sm-12 text-center">
                        <button type="button" class="btn btn-primary" id="grade_submit">
                            <i class="icon-ok icon-white"></i> 确认
                        </button>
                        &nbsp;&nbsp;
                        <button type="button" class="btn" onclick="javascript:$('#grade_modal').modal('hide');" id="return">
                            返回
                        </button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#coding_running').on('hidden.bs.modal', function () {
        $(this).find(".modal-body").html("<img src='/static/img/loading.gif' class='loding'/>");
    })
</script>

<div class="page-container">
    <!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->

    <!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
    <!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
    <!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->

    <div class="sidebar-menu toggle-others fixed">

        <div class="sidebar-menu-inner">

            <!-- include header -->
            <%@include file="unified/header.jsp" %>

            <!-- include left side -->
            <%@include file="unified/leftSide.jsp" %>

        </div>

    </div>

    <div class="main-content">

        <!-- User Info, Notifications and Menu Bar -->
        <jsp:include page="unified/navBar.jsp"/>

        <jsp:include page="business/${page}.jsp"/>

    </div>

</div>


<!-- Bottom Scripts -->
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/TweenMax.min.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/resizeable.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/joinable.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/xenon-api.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/xenon-toggles.js"></script>

<!-- Imported scripts on this page -->
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/xenon-widgets.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/devexpress-web-14.1/js/globalize.min.js"></script>

<!-- JavaScripts initializations and stuff -->
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/xenon-custom.js"></script>
<script src="<%=request.getContextPath() %>/static/js/sso.client.logout.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/calendar.js" charset="gbk"></script>

<script type="application/javascript">
    // 参数检查
    function checkParam(param, msg) {
        if (isNullOrEmpty(param, true)) {
            toastr.clear();
            toastr.warning(msg);
            return false;
        }
        return true;
    }

    function IsNum(num){
        var reNum=/^\d*$/;
        return(reNum.test(num));
    }
</script>
</body>
</html>
