package com.me.crm.interceptor;

import com.me.annotation.Limit;
import com.me.crm.container.ServiceProvider;
import com.me.crm.domain.SysPopedomPrivilege;
import com.me.crm.domain.SysUser;
import com.me.crm.service.ISysPopedomPrivilegeService;
import com.me.crm.util.SessionUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by DJ on 2017/4/25.
 */
public class LimitInterceptor extends MethodFilterInterceptor {
    public String doIntercept(ActionInvocation invocation) throws Exception {
        //获取请求的action对象
        Object action = invocation.getAction();
        //获取请求的方法名称
        String methodName = invocation.getProxy().getMethod();
        //获取action中的封装类
        Method method;
        method = action.getClass().getMethod(methodName, null);
        System.out.print("methodName" + methodName);
        //获取request对象
        HttpServletRequest request = ServletActionContext.getRequest();
        //检查注解
        boolean flag = isCheckLimit(request, method);
        if (!flag) {
            //没有权限
            return "popmsg_popedom";
        }
        //有权限，调用action中的方法
       String returnvalue=invocation.invoke();
        return returnvalue;

    }

    private boolean isCheckLimit(HttpServletRequest request, Method method) {
        if (method == null) {
            return false;
        }
        //获取当前登录的用户
        SysUser sysUser = SessionUtils.getSysUserFromSession(request);
        if (sysUser == null) {
            return false;
        }
        if (sysUser.getSysRole() == null) {
            return false;
        }
        //获取当前用户权限组的id
        String roleId = sysUser.getSysRole().getId();
        //处理注解
        boolean isAnnotationPresent = method.isAnnotationPresent(Limit.class);
        //不存在注解
        if (!isAnnotationPresent) {
            return false;
        }
        //存在注解
        Limit limit = method.getAnnotation(Limit.class);
        //获取注解上的值
        String module = limit.module();//模块名称
        String privilege = limit.privilege();//操作名称
        //如果登录用户的角色权限组id+注解上的值在部门操作表中存在，返回true
        boolean flag = false;
        //查询sys_popedom_privilege表中的说有数据
        ISysPopedomPrivilegeService sysPopedomPrivilegeService =
                (ISysPopedomPrivilegeService) ServiceProvider.getService(ISysPopedomPrivilegeService.SERVICE_NAME);
        List<SysPopedomPrivilege> list = sysPopedomPrivilegeService.findAllSysPopePrivileges();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                SysPopedomPrivilege s = list.get(i);
                if (s != null) {
                    if (roleId.equals(s.getId().getRoleId()) && module.equals(s.getId().getPopedomModule()) && privilege.equals(s.getId().getPopedomPrivilege())) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}

