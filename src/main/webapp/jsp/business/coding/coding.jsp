<%--
  Created by IntelliJ IDEA.
  User: 钱逊
  Date: 2017/5/12
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
<style type="text/css" media="screen">
    body {
        overflow: hidden;
    }

    #editor {
        margin: 0;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }
    #save{
        position: fixed;
        bottom:10px;
        right:120px;
    }
    #back{
        position: fixed;
        bottom:10px;
        right:10px;
    }
    #run{
        position: fixed;
        bottom:10px;
        right:65px;
    }

</style>
<div class="container-fluid form-box" >
<pre id="editor" style="margin-top:75.6px;"></pre>
</div>
<button type="button" class="btn"  id="save">保存</button>
<button type="button" class="btn"  id="run">运行</button>
<button type="button" class="btn"  id="back">返回</button>
<input type="hidden" id="subId" value="${submit.id}">
<script src="../../static/js/src-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
<script>

    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/tomorrow");
    editor.session.setMode("ace/mode/java");

    $("#editor").keyup(function(){
        coding.send(editor.getValue());
    });

    $(function(){
        coding = new WebSocket("ws://localhost:8080/mvc/ws/submit?id="+$("#subId").val() );
        coding.onopen = function (evnt) {
            console.log("coding connection success");
            coding.send(editor.getValue());
        };
        coding.onmessage = function (evnt) {
        };
        coding.onerror = function (evnt) {
        };
        coding.onclose = function (evnt) {
            console.log("update success");
        }
    });

    $("#back").click(function(){
        coding.send("#cancel");
        sessionStorage.clear();history.go(-1);
    });
    $("#save").click(function(){
        sessionStorage.clear();history.go(-1);
    });

    $("#run").click(function(){
        $("#coding_running").modal("show");
        request("/mvc/api/onlineRunning",{id:$("#subId").val()},function(data){
            if(data.head.state="success"){
                $("#coding_running .modal-body").html(data.body);
            }
        });
    });
</script>
<code style="display: none" id="content">${submit.content}</code>
<c:if test="${!empty submit}">
    <%--<pre style="display: none" id="content">${submit.content}</pre>--%>

    <script>
        editor.setValue($("#content").html());
    </script>
</c:if>

