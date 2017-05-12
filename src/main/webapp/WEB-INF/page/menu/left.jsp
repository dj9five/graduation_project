<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<html>
<head>
 <title>菜单</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/menu/css/jquery.treeview.css" />
 <link rel="stylesheet" href="${pageContext.request.contextPath}/menu/css/screen.css" />
 <script src="${pageContext.request.contextPath}/menu/js/jquery-1.4.2.js" type="text/javascript"></script>
 <script src="${pageContext.request.contextPath}/menu/js/jquery.treeview.js" type="text/javascript"></script>
<script type="text/javascript">
	$( function() {
		$("#tree").treeview( {
			collapsed : true,
			animated : "medium",
			control : "#sidetreecontrol",
			persist : "location"
		});
	})
</script>

</head>
<body ondblclick="ToggleNav();" style="background: #1B7CD0">
<div id="main">
<div id="sidetree">
	<div class="treeheader" style="font-size: large">菜单</div>
  
	<div id="sidetreecontrol"><a href="?#">菜单合拢</a> | <a href="?#">菜单展开</a></div>
		<ul id="tree">
			<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17" height="17">
				<strong style="font-size: large">客户管理</strong>
					<ul style="background: #1B7CD0">
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
							<a href="${pageContext.request.contextPath}/crm/companyAction_listziyuan.do"
							   target="rightFrame" style="color: #040205">资源客户</a></li>
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
							<a href="${pageContext.request.contextPath}/crm/companyAction_listqianzai.do"
							   target="rightFrame" style="color: #040205">潜在客户</a></li>
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
							<a href="${pageContext.request.contextPath}/crm/companyAction_listzhongyao.do" target="rightFrame"
							   style="color: #040205">重要客户</a></li>
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
							<a href="${pageContext.request.contextPath}/crm/companyAction_listzhengshi.do" target="rightFrame"
							   style="color: #040205">正式客户</a></li>
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
							<a href="${pageContext.request.contextPath}/crm/companyAction_listwuxiao.do" target="rightFrame"
							   style="color: #040205">无效客户</a></li>
					</ul>
				 </li>
			<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17" height="17">
				<strong style="font-size: large">系统设置 </strong>
					<ul style="background: #1B7CD0">
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
							<a href="${pageContext.request.contextPath}/crm/companyAction_list.do" target="rightFrame"
							   style="color: #040205 ">客户设置</a></li>
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
						           <a href="${pageContext.request.contextPath}/sys/sysUserGroupAction_list.do" target="rightFrame" style="color: #040205">部门设置</a></li>
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
						           <a href="${pageContext.request.contextPath}/sys/sysUserAction_list.do" target="rightFrame" style="color: #040205">人事管理</a></li>
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
							<a href="${pageContext.request.contextPath}/sys/sysRoleAction_list.do" target="rightFrame"
							   style="color: #040205">操作权限</a></li>
					</ul>
				 </li>

			<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17" height="17">
				<strong style="font-size: large">报表分析</strong>
					<ul style="background: #1B7CD0">
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
							<a href="${pageContext.request.contextPath}/report/xtsz1.jsp" target="rightFrame"
							   style="color: #040205">客户分类分析 </a></li>
						<li><img src="${pageContext.request.contextPath}/ui/images/menu/xtsz1.png" width="17"
								 height="17">
							<a href="${pageContext.request.contextPath}/report/xtsz1.jsp" target="rightFrame"
							   style="color: #040205">客户分析 </a></li>
					</ul>
				 </li>
		</ul>
		
	</div>
  	
</div>
<div id="divCollapsedNav" class="navTocColor" style="display:none;width:100%;height:100%;"> 
  <a href="javascript:ToggleNav();" title="展开导航框架" id="linkNavClosed">
      <img src="${pageContext.request.contextPath}/menu/images/toc2.gif" alt="展开导航框架" border="3"></img>
  </a> 
</div>
<script src="${pageContext.request.contextPath}/menu/js/left.js"></script>

</body>
</html>


