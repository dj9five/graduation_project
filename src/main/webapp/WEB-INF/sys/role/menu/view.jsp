<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择菜单</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>

<style type="text/css">
<!--
fieldset div {
	float:left;
	width:24%;
	text-align:left;
	line-height:25px;
}
td div {
	float:left;
	width:24%;
	text-align:left;
	line-height:25px;
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
     $("input[type=checkbox][name=menuModule]").attr("checked","checked");
  }

  function UnSelectAllBox(){
	     $("input[type=checkbox][name=menuModule]").attr("checked",null);
  }
</script>
</head>
<body>
<form name="ActionForm" method="post" action="${pageContext.request.contextPath}/sysMenuAction.do">
<input type="hidden" name="method" value="update">
<input type="hidden" name="roleId" value="ff8080812ae1b760012ae1c3dd7f0001">
<br/>
<div class="control">
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';" 
		    onClick="SelectAllBox()">
		    <img src="${pageContext.request.contextPath}/ui/images/button/quanbuxz.png" border='0' 
		    align='absmiddle'>&nbsp;全部选中</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="UnSelectAllBox()">
	        <img src="${pageContext.request.contextPath}/ui/images/button/quanbubxz.png" border='0' 
	        align='absmiddle'>&nbsp;全部不选中</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="document.ActionForm.submit();">
	        <img src="${pageContext.request.contextPath}/ui/images/button/baocun.png" border='0' 
	        align='absmiddle'>&nbsp;保存</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="parent.close();">
	        <img src="${pageContext.request.contextPath}/ui/images/button/guanbi.png" border='0' 
	        align='absmiddle'>&nbsp;关闭</button>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#6A82A8">
	<tr>
		<td align="center" height="25"><span style="color:#FFFFFF; font-weight:bold">操作权限组：销售部操作权限</span></td>
	</tr>
</table>
<div class="border" style="padding:3px">
 
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>

    
	   <fieldset style='padding:5px;clear:left;'>
	      <legend><input type='checkbox' class='checkbox' name='menuModule' value='company,company' 
	           id='company_company' onClick='goSelect(this.id)' 
		               checked='checked' >客户管理
	      </legend>
	      
	          
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='company,base'    checked='checked'
		                   id='company_base' onClick='goSelect(this.id)' >客户拜访</div>
		      
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='company,linkMan'    checked='checked'
		                   id='company_linkMan' onClick='goSelect(this.id)' >联系人列表</div>
		      
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='company,linkTouch'    checked='checked'
		                   id='company_linkTouch' onClick='goSelect(this.id)' >联系记录列表</div>
		      
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='company,rule'    checked='checked'
		                   id='company_rule' onClick='goSelect(this.id)' >客户查重设置 </div>
		      
	
	      
	   </fieldset>

    
	   <fieldset style='padding:5px;clear:left;'>
	      <legend><input type='checkbox' class='checkbox' name='menuModule' value='sys,sys' 
	           id='sys_sys' onClick='goSelect(this.id)' 
		               checked='checked' >系统设置 
	      </legend>
	   
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='sys,group'    checked='checked'
		                   id='sys_group' onClick='goSelect(this.id)' >部门设置</div>
		      
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='sys,user'    checked='checked'
		                   id='sys_user' onClick='goSelect(this.id)' >人事管理</div>
		      
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='sys,role'    checked='checked'
		                   id='sys_role' onClick='goSelect(this.id)' >操作权限组</div>
		      
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='sys,province'    checked='checked'
		                   id='sys_province' onClick='goSelect(this.id)' >省份资料</div>
		      
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='sys,city'    checked='checked'
		                   id='sys_city' onClick='goSelect(this.id)' >城市资料</div>
		      
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='sys,code'    checked='checked'
		                   id='sys_code' onClick='goSelect(this.id)' >编码规则</div>
		
	      
	   </fieldset>

	   <fieldset style='padding:5px;clear:left;'>
	      <legend><input type='checkbox' class='checkbox' name='menuModule' value='report,report' 
	           id='report_report' onClick='goSelect(this.id)' 
		               checked='checked' >报表与分析
	      </legend>

		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='report,khflfx'    checked='checked'
		                   id='report_khflfx' onClick='goSelect(this.id)' >客户分类分析 </div>
		      
	      
	          
		         <div><input type='checkbox' class='checkbox' name='menuModule' 
		             value='report,khfx'    checked='checked'
		                   id='report_khfx' onClick='goSelect(this.id)' >客户分析 </div>
		      
	      
	   </fieldset>
</td>
	</tr>
</table>

</div>
</form>
</body>
</html>