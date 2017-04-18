<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>操作权限组修改</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
</head>
<body>
<form name="ActionForm" method="post" action="${pageContext.request.contextPath}/sys/sysRoleAction_update.do">
<input type="hidden" name="id" value="2">
<div class="mtitle">
	<div class="mtitle-row">&nbsp;</div>
	操作权限组修改
</div>
<br>
<div class="control">
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  onClick="if(check()) document.ActionForm.submit();"><img src="${pageContext.request.contextPath}/ui/images/button/baocun.png" border='0' align='absmiddle'>&nbsp;保存</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  onClick="forward('role.do?method=list')"><img src="${pageContext.request.contextPath}/ui/images/button/fanhui.png" border='0' align='absmiddle'>&nbsp;返回</button>
</div>

<table width="100%" border="0" cellspacing="0" class="tabForm">
  <tr>
    <th colspan="4" align="left" class="th_head">
		<div id="menuArrow1" style="background:url(${pageContext.request.contextPath}/ui/images/down.gif) no-repeat center;float:left;">&nbsp;</div>
		<div id="menuTitle1" style="font-weight:bold">基本信息</div>
	</th>
  </tr>
  <tr>
  	<td>
		<div id="menu1">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="16%"></td>
				<td width="34%"></td>
				<td width="16%"></td>
				<td width="34%"></td>
			</tr>			
			<tr>
				<td class="red">名称</td>
				<td><input name="name" type="text" id="name" style="width:90%" value=""></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td height="40" valign="top">备注</td>
				<td colspan="3"><textarea name="remark" rows="3" id="remark" style="width:96%"></textarea></td>
			</tr>
  		</table>
		</div>
	</td>
  </tr>
</table>
<br>
</form>



</body>
</html>