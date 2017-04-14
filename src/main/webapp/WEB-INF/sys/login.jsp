<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@page import="java.net.URLDecoder" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>客户关系管理系统</title>
    <link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>
    <style>
        .font {
            color: #104C78;
            font-size: 30pt;
            font-family: "黑体"
        }

        .td {
            font-size: 15pt;
            font-family: "黑体";
            height: 30px;
        }

        INPUT {
            background-color: #FFFFFF;
        }

        body {
            margin: 0px;
        }
    </style>
    <script type="text/javascript">
        function changeCheckNum() {
            var checkNumImage_ = document.getElementById("checkNumImage");
            checkNumImage_.src = "${pageContext.request.contextPath}/image.jsp?timeStamp=" + new Date().getTime();
        }

        function checkSubmit() {
            if ($("#name").val().length <= 0) {
                alert("登陆的用户名不能为空");
                return false;
            }
            if ($("#password").val().length <= 0) {
                alert("登陆的密码不能为空");
                return false;
            }
            /*if($("#checkNum").val().length<=0){
             alert("验证码不能为空");
             return false;
             }*/
            document.forms[0].submit();
        }
    </script>
</head>
<BODY background="ui/" topmargin="0" leftmargin="0"
      onLoad="if (window.location != window.top.location) window.top.location.href=window.location.href; document.all.userName.focus();setUserName();">

<%
    String name = "";
    String psw = "";
    String checked = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if ("name".equals(cookie.getName())) {
                name = URLDecoder.decode(cookie.getValue(), "utf-8");
                checked = "checked";
            }
            if ("psw".equals(cookie.getName())) {
                psw = cookie.getValue();
            }
        }
    }


%>

<form name="form1" method="post" action="${pageContext.request.contextPath}/sys/sysUserAction_isLogin.do">
    <TABLE width="100%" cellpadding="0" cellspacing="0" id="header">
        <TR>
            <TD>
                <TABLE height="80" cellpadding="0" cellspacing="0" width="100%">
                    <TR>
                        <TD background="${pageContext.request.contextPath}/ui/images/ban_2.gif">&nbsp;</TD>
                        <TD width="181" background="${pageContext.request.contextPath}/ui/images/ban_2.gif"></TD>
                    </TR>
                </TABLE>
            </TD>
        </TR>
        <TR>
            <TD height="119" background="${pageContext.request.contextPath}/ui/images/bg.gif">&nbsp;</TD>
        </TR>
        <TR>
            <TD height="300">
                <TABLE width="100%" cellpadding="0" cellspacing="0">
                    <TR>
                        <TD width="540" valign="top">
                            <TABLE width="100%" cellpadding="0" cellspacing="0">
                                <TR>
                                    <TD height="140" valign="top" align="center">
                                        <b><font class="font" size="30px" color="#00ced1">客户关系管理系统</font></b>
                                        <br>
                                        <font color="#FF0000"></font><br>
                                        <TABLE cellpadding="0" cellspacing="0">
                                            <TR>
                                                <TD class="td">用户帐号：</TD>
                                                <TD class="td">
                                                    <input name="name" type="text" value="<%= name %>"
                                                           id="name"/><s:fielderror fieldName="name"/></TD>
                                            </TR>
                                            <TR>
                                                <TD class="td">登录密码：</TD>
                                                <TD class="td"><input name="password" type="password" value="<%= psw %>"
                                                                      id="password"></TD>
                                            </TR>
                                            <TR>
                                                <TD class="td">验&nbsp;证&nbsp;码：</TD>
                                                <TD class="td"><input name="checkNum" type="text" value="" id="checkNum"
                                                                      style="width: 80">
                                                    <img id="checkNumImage"
                                                         src="${pageContext.request.contextPath}/image.jsp" height="19"
                                                         align="absmiddle" onClick="changeCheckNum()"
                                                         title="点击换一张" style="cursor:hand"><s:fielderror
                                                            fieldName="checkNum"/></TD>
                                            </TR>
                                            <TR>
                                                <TD class="td">记&nbsp;住&nbsp;我：</TD>
                                                <TD class="td"><input name="rememberMe" type="checkbox" id="rememberMe"
                                                                      value="yes" class="checkbox" <%=checked %>></TD>
                                            </TR>
                                        </TABLE>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD align="center"><img src="${pageContext.request.contextPath}/ui/images/login.png"
                                                            id="login"
                                                            onClick="checkSubmit();" style="cursor:hand" width="90"
                                                            height="30">
                                        &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/ui/images/reset.png"
                                                         id="reset"
                                                         onClick="" style="cursor:hand" width="90" height="30"></TD>
                                </TR>
                            </TABLE>
                        </TD>
                    </TR>
                </TABLE>
            </TD>
        </TR>
        <TR>
            <TD height="5">&nbsp;</TD>
        </TR>
    </TABLE>
</form>
</BODY>
</html>