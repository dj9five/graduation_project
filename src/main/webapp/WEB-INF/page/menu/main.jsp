<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>客户关系管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<frameset rows="80,*" cols="*">
    <frame src="${pageContext.request.contextPath}/sys/menuAction_top.do" name="topFrame" id="topFrame" scrolling="NO"
           noresize="noresize" frameborder="0">
    <frameset cols="20%,80%" id="frmstOuter">
        <frame src="${pageContext.request.contextPath}/sys/menuAction_left.do" name="leftFrame" id="leftFrame"
               scrolling="auto""  frameborder="0" framespacing="1px" bordercolor="#4faad8">
        <frame src="${pageContext.request.contextPath}/sys/menuAction_right.do" name="rightFrame" id="rightFrame" scrolling="auto""  frameborder="0" framespacing="1px" bordercolor="#4faad8">
    </frameset>
</frameset>
<noframes>
    <body >
    <h1>123</h1>
    </body>
</noframes>
</html>