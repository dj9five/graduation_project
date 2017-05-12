<%@ page language="java" pageEncoding="utf-8" import="java.util.Calendar" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.GregorianCalendar" %>
<html>
<head>
    <title>客户关系管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!--
    <link href="${pageContext.request.contextPath}/menu/css/top.css" rel="stylesheet" type="text/css">
    -->
</head>
<script language="javascript">
    var currentDate = new Date(<%=new java.util.Date().getTime()%>);
    function run() {
        currentDate.setSeconds(currentDate.getSeconds() + 1);          document.getElementById("currentTime").innerHTML = currentDate.toLocaleString();      }
    window.setInterval("run();", 1000);
</script>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background: #1B7CD0">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="top">
    <tr>
        <td class="logo">
            <b><h1 style="text-align: center" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                客户关系管理系统</h1></b>
            <div class="subNav">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <td> 网络13-2 段捷的毕设系统！今天是：<nobr id="currentTime" ></nobr></td>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
