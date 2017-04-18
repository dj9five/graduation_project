<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择权限</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>

<style type="text/css">
<!--
fieldset div {
	float:left;
	width:24%;
	line-height:25px;
	text-align:left;
}
td div {
	float:left;
	width:24%;
	line-height:25px;
	text-align:left;
}
-->
</style>
<script language="javascript">
   function goSelect(id){
      var valueStr=$("#"+id).val();
	  var array=valueStr.split(",");
	  if(array[0] != array[1]){
	     if($("#"+id)[0].checked){
		 	 var supid=array[0]+"_"+array[0];
		 	 $("#"+ supid).attr("checked","checked"); 
		 }else{
		 	 var $jihe=$("input[type='checkbox'][value^="+array[0]+"]:not([value$="+array[0]+"])"); 
			 var flag=false;
			 
			 $jihe.each(function(index,domEle){
				 if(this.checked){
				 	flag=true;
					return;
				 }
			 });
			 
			 if(!flag){
			 	  var supid=array[0]+"_"+array[0];
		 	       $("#"+ supid).attr("checked",null); 
			 }
		 }
	  }
	  
	  if(array[0]==array[1]){
	  	 if($("#"+id)[0].checked){
		     $("input[type='checkbox'][value^="+array[0]+"]").attr("checked","checked");   	
		 }else{
		 	  $("input[type='checkbox'][value^="+array[0]+"]").attr("checked",null); 
		 }
	  }
  }
  
  function SelectAllBox(){
     $("input[type=checkbox][name=popedomModule]").attr("checked","checked");
  }

  function UnSelectAllBox(){
	     $("input[type=checkbox][name=popedomModule]").attr("checked",null);
  }
</script>
</head>

<body>
<s:form name="ActionForm" method="post" action="sysRoleAction_updatePopedom.do" namespace="/sys">
<s:hidden name="roleId" value="%{#request.sysRole.id}" />
<br/>
<div class="control">
	<button type='button' class='button' onMouseOver="this.className='button_over';" 
	onMouseOut="this.className='button';"  onClick="SelectAllBox()"><img src="${pageContext.request.contextPath}/ui/images/button/quanbuxz.png" border='0' align='absmiddle'>&nbsp;全部选中</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" 
	onMouseOut="this.className='button';"  onClick="UnSelectAllBox()"><img src="${pageContext.request.contextPath}/ui/images/button/quanbubxz.png" border='0' align='absmiddle'>&nbsp;全部不选中</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" 
	onMouseOut="this.className='button';"  onClick="document.ActionForm.submit();"><img src="${pageContext.request.contextPath}/ui/images/button/baocun.png" border='0' align='absmiddle'>&nbsp;保存</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" 
	onMouseOut="this.className='button';"  onClick="parent.close();"><img src="${pageContext.request.contextPath}/ui/images/button/guanbi.png" border='0' align='absmiddle'>&nbsp;关闭</button>
</div>

<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#6A82A8">
	<tr>
		<td align="center" height="25"><span style="color:#FFFFFF; font-weight:bold">操作权限组：${sysRole.name}</span></td>
	</tr>
</table>
<div class="border" style="padding:3px">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
		
		
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='company,company'
			   id='company_company' 
			   onClick='goSelect(this.id)'>客户拜访
			</legend>
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='company,add'
			    id='company_add' 
			    onClick='goSelect(this.id)'>添加</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='company,update'
			    id='company_update' 
			    onClick='goSelect(this.id)'>修改</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='company,delete'
			    id='company_delete' 
			    onClick='goSelect(this.id)'>删除</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='company,list'
			    id='company_list' 
			    onClick='goSelect(this.id)'>查询</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='company,nextTouchDate'
			    id='company_nextTouchDate' 
			    onClick='goSelect(this.id)'>设置下次联系时间</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='company,dispensePerson'
			    id='company_dispensePerson' 
			    onClick='goSelect(this.id)'>经手人变更</div>
	
		     
			</fieldset>
		
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='linkman,linkman'
			   id='linkman_linkman' 
			   onClick='goSelect(this.id)'>联系人管理
			</legend>
		
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linkman,add'
			    id='linkman_add' 
			    onClick='goSelect(this.id)'>添加</div>
		       
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linkman,update'
			    id='linkman_update' 
			    onClick='goSelect(this.id)'>修改</div>
		       
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linkman,delete'
			    id='linkman_delete' 
			    onClick='goSelect(this.id)'>删除</div>
		       
		    	
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linkman,list'
			    id='linkman_list' 
			    onClick='goSelect(this.id)'>查询</div>
		       
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linkman,view'
			    id='linkman_view' 
			    onClick='goSelect(this.id)'>查看列表</div>
		       
		    	
			</fieldset>
		
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='linktouch,linktouch'
			   id='linktouch_linktouch' 
			   onClick='goSelect(this.id)'>联系记录管理
			</legend>
				
			
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linktouch,add'
			    id='linktouch_add' 
			    onClick='goSelect(this.id)'>添加</div>
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linktouch,update'
			    id='linktouch_update' 
			    onClick='goSelect(this.id)'>修改</div>
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linktouch,delete'
			    id='linktouch_delete' 
			    onClick='goSelect(this.id)'>删除</div>
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linktouch,list'
			    id='linktouch_list' 
			    onClick='goSelect(this.id)'>查询</div>
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='linktouch,view'
			    id='linktouch_view' 
			    onClick='goSelect(this.id)'>查看列表</div>
		     
			</fieldset>
		
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='rule,rule'
			   id='rule_rule' 
			   onClick='goSelect(this.id)'>客户策略管理
			</legend>
		
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='rule,update'
			    id='rule_update' 
			    onClick='goSelect(this.id)'>修改</div>

			</fieldset>
		
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='group,group'
			   id='group_group' 
			   onClick='goSelect(this.id)'>部门设置
			</legend>
		
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='group,add'
			    id='group_add' 
			    onClick='goSelect(this.id)'>添加</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='group,update'
			    id='group_update' 
			    onClick='goSelect(this.id)'>修改</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='group,delete'
			    id='group_delete' 
			    onClick='goSelect(this.id)'>删除</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='group,list'
			    id='group_list' 
			    onClick='goSelect(this.id)'>查询</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='group,UserSet'
			    id='group_UserSet' 
			    onClick='goSelect(this.id)'>人员设置</div>
		       
		    
			   
		     
			</fieldset>
		
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='user,user'
			   id='user_user' 
			   onClick='goSelect(this.id)'>人事管理
			</legend>
	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='user,add'
			    id='user_add' 
			    onClick='goSelect(this.id)'>添加</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='user,update'
			    id='user_update' 
			    onClick='goSelect(this.id)'>修改</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='user,delete'
			    id='user_delete' 
			    onClick='goSelect(this.id)'>删除</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='user,list'
			    id='user_list' 
			    onClick='goSelect(this.id)'>查询</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='user,yes'
			    id='user_yes' 
			    onClick='goSelect(this.id)'>启用</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='user,no'
			    id='user_no' 
			    onClick='goSelect(this.id)'>停用</div>
		       
	
		     
			</fieldset>
		
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='role,role'
			   id='role_role' 
			   onClick='goSelect(this.id)'>操作权限组
			</legend>
				
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='role,add'
			    id='role_add' 
			    onClick='goSelect(this.id)'>添加</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='role,update'
			    id='role_update' 
			    onClick='goSelect(this.id)'>修改</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='role,delete'
			    id='role_delete' 
			    onClick='goSelect(this.id)'>删除</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='role,list'
			    id='role_list' 
			    onClick='goSelect(this.id)'>查询</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='role,menuSet'
			    id='role_menuSet' 
			    onClick='goSelect(this.id)'>菜单设置</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='role,popedomSet'
			    id='role_popedomSet' 
			    onClick='goSelect(this.id)'>操作设置</div>
		       

		     
			</fieldset>

			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='province,province'
			   id='province_province' 
			   onClick='goSelect(this.id)'>省份资料
			</legend>
				
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='province,add'
			    id='province_add' 
			    onClick='goSelect(this.id)'>添加</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='province,update'
			    id='province_update' 
			    onClick='goSelect(this.id)'>修改</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='province,delete'
			    id='province_delete' 
			    onClick='goSelect(this.id)'>删除</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='province,list'
			    id='province_list' 
			    onClick='goSelect(this.id)'>查询</div>
		       
		     
			</fieldset>
		
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='city,city'
			   id='city_city' 
			   onClick='goSelect(this.id)'>城市资料
			</legend>
	

			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='city,add'
			    id='city_add' 
			    onClick='goSelect(this.id)'>添加</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='city,update'
			    id='city_update' 
			    onClick='goSelect(this.id)'>修改</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='city,delete'
			    id='city_delete' 
			    onClick='goSelect(this.id)'>删除</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='city,list'
			    id='city_list' 
			    onClick='goSelect(this.id)'>查询</div>	   
		     
			</fieldset>
		
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='code,code'
			   id='code_code' 
			   onClick='goSelect(this.id)'>编码规则
			</legend>
				

			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='code,add'
			    id='code_add' 
			    onClick='goSelect(this.id)'>添加</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='code,update'
			    id='code_update' 
			    onClick='goSelect(this.id)'>修改</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='code,delete'
			    id='code_delete' 
			    onClick='goSelect(this.id)'>删除</div>
		       
		    	
			   
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='code,list'
			    id='code_list' 
			    onClick='goSelect(this.id)'>查询</div>
			</fieldset>
			<fieldset style='padding:5px;clear:left;'>
			<legend><input type='checkbox' class='checkbox' name='popedomModule'   
			   checked='checked'
			   value='report,report'
			   id='report_report' 
			   onClick='goSelect(this.id)'>报表与分析
			</legend>
	
			    <div><input type='checkbox' class='checkbox' name='popedomModule' 
			         checked='checked'
			    value='report,khflfx'
			    id='report_khflfx' 
			    onClick='goSelect(this.id)'>客户分类分析</div>
			</fieldset>
</td>
</tr>
</table>
</div>
</s:form>
</body>
</html>