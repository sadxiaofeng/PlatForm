<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
<style>
    table td{
        color:#000 !important;
    }
</style>
<div>
    <table class="table table-model-2 table-hover table-condensed" id="">
        <thead>
        <tr>
            <th>任务编号</th>
            <th>标题</th>
            <th>创建时间</th>
            <th>截止时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <c:if test="${!empty experimentList}">
            <c:forEach items="${experimentList}" var="experiment" varStatus="i">
                <tr>
                    <td>${experiment.id}</td>
                    <td>${experiment.title}</td>
                    <td>${experiment.createTime}</td>
                    <td><fmt:formatDate value="${experiment.deadLine}" pattern="yyyy-MM-dd hh:mm"/></td>
                    <td>${{0:"未发布",
                            1:"已发布"
                            }[experiment.status]}</td>
                    <td>
                        <c:if test="${experiment.status == '0'}">
                            <a href="javascript:void(0);" class="${experiment.id }"
                               id="publish"><span class="fa-wrench" title="发布任务"></span> </a>
                            <a href="javascript:void(0);" class="${experiment.id }"
                               id="delete"><span class="fa-remove" title="删除"></span> </a>
                        </c:if>
                        <a href="javascript:void(0);" class="${experiment.id }"
                           id="view"><span class="fa-eye" title="查看要求"></span> </a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

        <c:if test="${empty experimentList}">
            <tr>
                <!-- 需要修改列数dx -->
                <td colspan="20" align="center" bgcolor="#EFF3F7" style="font-size:16px;font-weight:bold;">没有找到相应的记录</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<input type="hidden" id="courseId" value="${courseId}"/>
<input type="hidden" id="classId" value="${classId}"/>

<script>
    $("#publish").click(function(){
        var param ={
            id:$(this).attr("class"),
            classId:$("#classId").val()
        }
        request("/mvc/exp/publishExp",param,function(data){
            if(data.head.status="success"){
                alert("发布成功！");
                window.location.href="/mvc/exp/viewTask?courseId="+$("#courseId").val()+"&classId="+$("#classId").val();
            }
        })
    });
    $("#delete").click(function(){

    });
    $("#view").click(function(){

    });
</script>