<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>客户拜访</title>
    <link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/ui/js/win.js" type="text/javascript"></script>

    <!--处理日期 开始 -->
    <script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick-zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/calendar.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.css"
          type="text/css">
    <!--处理日期结束  -->
</head>
<body>
<div class="mtitle">
    <div class="mtitle-row">&nbsp;</div>
    <b><span id="menu_selected" class="menu_selected" style="font-size: larger">系统操作日志</span></b>
</div>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabForm">
    <tr>
        <th class="th_head">
            <div id="menuArrow1"
                 style="background:url(${pageContext.request.contextPath}/ui/images/down.gif) no-repeat center;float:left;">
                &nbsp;
            </div>
            
        </th>
    </tr>
    <tr>
        <td colspan="2">
        </td>
    </tr>
</table>
<br>
<h3><img src="${pageContext.request.contextPath}/ui/images/menu/khlb.png" border="0">&nbsp;操作日志列表</h3>
<span id="slt_ids_count1"></span>

<div class="border">
    <s:form method="post" action="operateAction_list.do" namespace="/crm">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="PowerTable" class="PowerTable">
            <tr>
                <td width="3%" class="listViewThS1">
                    <s:checkbox name="checkall" id="checkall" value="" cssClass="checkbox" onclick="checkAll()"/>
                </td>
                <td width="10%" class="listViewThS1">操作人</td>
                <td width="10%" class="listViewThS1">操作事件</td>
                <td width="10%" class="listViewThS1">操作时间</td>
                <td width="30%" class="listViewThS1">操作描述</td>
            </tr>
            <!-- data -->
            <s:if test="#request.operateLog!=null">
                <s:iterator value="#request.operateLog" var="operateLog">
                    <tr>
                        <td>
                            <s:checkbox name="ids" fieldValue="%{#opetateLog.id}" cssClass="checkbox"
                                        onclick="changeCheckCount();"/>
                        </td>
                        <td><s:property value="#operateLog.cnname"/></td>
                        <td><s:property value="#operateLog.actionType"/></td>
                        <td><s:property value="#operateLog.actionDate"/></td>
                        <td><s:property value="#operateLog.actionContent"/></td>
                    </tr>
                </s:iterator>
            </s:if>
        </table>
    </s:form>
</div>
</body>
</html>