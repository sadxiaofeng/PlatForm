<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/inc.jsp" %>
<style>
    td{
        color:#000 !important;
        font-weight: 500 !important;
    }
    .new{
        color:red;
        font-weight: 500;
    }
</style>
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
                <td>${submit.experimentId}<c:if test="${submit.isRead==0}"><span class="new">(new)!</span></c:if></td>
                <td>${submit.course.name}</td>
                <td>${submit.course.teacher.name}</td>
                <td>${submit.experiment.title}</td>
                <td>
                    <fmt:formatDate value="${submit.createTime}"
                                    pattern="yyyy-MM-dd HH:mm aa" type="date" dateStyle="long"/></td>
                <td>${{0:"未开始",
                        1:"完成中",
                        2:"完成未审批",
                        3:"已批阅(优)",
                        4:"已批阅(良)",
                        5:"已批阅(中)"
                }[submit.status]}</td>
                <td>
                    <a href="javascript:void(0);" class="editexp" id="${submit.id}"><span class="fa-pencil-square-o" title="编写"></span> </a>
                    <a href="javascript:void(0);" class="view" id="${submit.id}"><span class="fa-eye" title="查看代码"></span> </a>
                </td>
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
<input type="hidden" id="courseId" value="${courseId}">
<script>
    $(".editexp").each(function(){
        $(this).click(function() {
            $id = $(this).attr("id");
            window.location.href = "/mvc/exp/editExp?subId=" + $id + "&courseId=" + $("#courseId").val();
        });
    });

    $(".view").each(function(){
        $(this).click(function() {
            $id = $(this).attr("id");
            window.location.href = "/mvc/exp/viewCode?subId=" + $id + "&courseId=" + $("#courseId").val();
        });
    });

    $(function(){
        var courseId = $("#courseId").val();
        $("#exp_alert .modal-body div").each(function(){
            var $target = $(this);
            var id = $target.attr("courseid");
            if(courseId==id){
                $target.remove();
            }
        });
    })
</script>