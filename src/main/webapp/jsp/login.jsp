<%--
  Created by IntelliJ IDEA.
  User: 钱逊
  Date: 2017/4/15
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Xenon Boostrap Admin Panel"/>
    <meta name="author" content=""/>

    <title>实验教学平台登录</title>

    <link rel="shortcut icon" href="<%=request.getContextPath() %>/static/img/favicon.ico"/>

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

    <script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/jquery-1.11.1.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body class="page-body login-page login-light">


<div class="login-container" style="margin-left: 38%">

    <div class="row">

        <div class="col-sm-6">

            <!-- Errors container -->
            <div class="errors-container">
            </div>

            <!-- Add class "fade-in-effect" for login form effect -->
            <form method="post" id="login" class="login-form fade-in-effect">

                <div class="login-header" style="text-align: center;">
                    <%--<img src="<%=request.getContextPath() %>/static/img/login_logo.png" width="200" alt=""/>--%>
                    <span class="logo" style="font-size: 30px;font-weight: bold;margin-top:20px;">实验教学平台</span>

                </div>

                <div class="form-group">
                    <input type="text" class="form-control" name="username" id="username" autocomplete="on"
                           placeholder="账号"/>
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" name="passwd" id="password" autocomplete="on"
                           placeholder="密码"/>
                </div>

                <div class="form-group">
                    <button id="loginBtn" class="btn btn-primary  btn-block text-left">
                        <i class="fa-lock"></i>
                        登录
                    </button>
                </div>

                <div class="login-footer">
                    <span style="color:red;"></span>
                </div>
            </form>

        </div>

    </div>
    <input type="hidden" id="realPath" value="<%=request.getContextPath() %>">
</div>
<!-- Bottom Scripts -->
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/TweenMax.min.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/resizeable.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/joinable.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/xenon-api.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/xenon-toggles.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/toastr/toastr.min.js"></script>


<!-- JavaScripts initializations and stuff -->
<script src="<%=request.getContextPath() %>/static/plugins/xenon/assets/js/xenon-custom.js"></script>
<script type="application/javascript">
    $(document).ready(function($) {
        // Reveal Login form
        setTimeout(function () {
            $(".fade-in-effect").addClass('in');
        }, 1);

        // Validation and Ajax action
        $("form#login").validate({
            rules: {
                username: {
                    required: true
                },

                passwd: {
                    required: true
                }
            },

            messages: {
                username: {
                    required: '请输入账号'
                },

                passwd: {
                    required: '请输入密码'
                }
            },

            // Form Processing via AJAX
            submitHandler: function (form) {
                show_loading_bar(70); // Fill progress bar to 70% (just a given value)

                $('#loginBtn').attr("disabled", true);

                $.ajax({
                    url: "auth/accessToken",
                    method: 'POST',
                    dataType: 'json',
                    data: {
                        account: $("#username").val(),
                        password: $("#password").val()
                    },
                    success: function (resp) {
                        if (resp.head.state == "success") {
                            window.location.href = 'home';
                        } else {
                            $(".login-footer span").html("用户名或密码不正确");
                            $('#loginBtn').attr("disabled", false);
                        }
                    }
                });
            }
        });

        //Set Form focus
        $("form#login .form-group:has(.form-control):first .form-control").focus();
    });
</script>
</body>