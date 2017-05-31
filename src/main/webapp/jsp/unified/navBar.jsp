<%@ page import="com.pojo.User" %>
<%@ page import="com.util.CookieUtil" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    User user = CookieUtil.getCurrentUser(request);
%>
<style>
    #blareImg{
        width:20px;
        height: 20px;
        box-sizing: content-box;
        margin-top:15px;
        margin-right:20px;
        transform: translateY(50%);
        animation: mymove 1.5s infinite;
        opacity: 1;
        display: none;
    }
    #aliw{
        width:20px;
        height: 20px;
        box-sizing: content-box;
        margin-top:15px;
        margin-right:20px;
        transform: translateY(50%);
        animation: mymove 1.5s infinite;
        opacity: 1;
        display: none;
    }
    @keyframes mymove {
        0%, 100% {
            opacity: 1;
        }

        49% {
            opacity: 1;
        }

        50% {
            opacity: 0;
        }

        99% {
            opacity: 0;
        }
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

    <ul class="user-info-menu left-links list-inline list-unstyled" >

        <li class="dropdown user-profile">
            <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                <span>
                    联系人
                    <i class="fa-angle-down"></i>
                </span>
            </a>

            <ul class="dropdown-menu user-profile-menu list-unstyled" id="contacts" style="z-index:100;width:100px;overflow: auto;max-height:200px;">

            </ul>
        </li>
    </ul>

    <ul class="user-info-menu left-links list-inline list-unstyled">
        <a href="javascript:void(0);">
            <img src="/static/img/aliw.jpg" id="aliw" />
        </a>
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
        <a href="javascript:void(0);">
            <img src="/static/img/blare.png" id="blareImg" />
        </a>
    </ul>

</nav>

<script type="text/javascript">
    $(function(){
       request("/mvc/api/getContacts",{},function(data){
           if(data.head.state=="success"){
               var userlist = eval("("+data.body+")");
               var content = "";
               for(var i=0;i<userlist.length;i++){
                   content += "<li><a account='"+userlist[i].account+"' href='javascript:void(0);'>"+userlist[i].name+"</a></li>";
               }
               $("#contacts").append(content);
           }
       });

        $(document).on("click","#contacts a",function(){
            $("#modal_send").modal("show");
            $("#modal_send h4").text("给"+$(this).text()+"发送信息");
            $("#send_submit").attr("account",$(this).attr("account"));
        });

        bulid();
    });

    $('#logoutBtn').on('click', function (ev) {
        ev.preventDefault();
        $("#logout_modal").modal('show');
    });

    websocket = new WebSocket("ws://localhost:8080/mvc/ws/login?id="+<%=user.getAccount()%>);
    websocket.onopen = function (evnt) {
        console.log("connection success");
    };
    websocket.onmessage = function (evnt) {
        var array = eval("("+evnt.data+")");
        //新任务发布信息
        if(array.type==1){
            $("#blareImg").css({"display":"block"});
            var target = array.message;
            var alert_content = "<div courseid='"+target.courseId+"'>"+target.experiment.creator.name+"老师的新任务："+ "<a class='underLine' href='/mvc/exp/listView?courseId="+target.courseId+"'>"+ target.experiment.title +"</a>" +"</div>"
            $("#exp_alert .modal-body").append(alert_content);
        }else{
            bulid();
        }
    };
    websocket.onerror = function (evnt) {
    };
    websocket.onclose = function (evnt) {
    }

    $("#blareImg").click(function(){
        $('#exp_alert').modal('show');
    });

    var interval = setTimeout(function(){
        clearTimeout(interval);
        request("/mvc/api/getNewExp",{},function(data){
            if(data.head.state=="success"){
                var array = eval("("+data.body+")");
                if(Object.prototype.toString.call(array)=='[object Array]' && array.length!=0){
                    $("#blareImg").css({"display":"block"});
                    var alert_content = "";
                    for(var i=0;i<array.length;i++){
                        alert_content += "<div courseid='"+array[i].courseId+"'>"+array[i].experiment.creator.name+"老师的新任务："+ "<a class='underLine' href='/mvc/exp/listView?courseId="+array[i].courseId+"'>"+ array[i].experiment.title +"</a>" +"</div>"
                    }
                    $("#exp_alert .modal-body").append(alert_content);
                }

            }
        });
    },500);


    function bulid(){
        request("/mvc/message/messageCount",{},function(data){
            if(data.head.state=="success"){
                var count = eval("("+data.body+")");
                var content="";
                for(var i=0;i<count.length;i++){
                    content += "<div><a href='javascript:void(0)' class='message_link underLine' account='"+count[i].account+"'>"+count[i].name+"</a>发给你"+count[i].count+"条留言</div>";
                }
                $("#modal_viewMessage .modal-body").html(content);
                if(content!=""){
                    $("#aliw").css({"display":"block"});
                }else{
                    $("#aliw").css({"display":"none"});
                }
            }
        });
    }

    $("#aliw").click(function(){
        $('#modal_viewMessage').modal('show');
    });
</script>
	