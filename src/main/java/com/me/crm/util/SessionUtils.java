package com.me.crm.util;

import com.me.crm.domain.SysUser;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by DJ on 2017/4/21.
 */
public class SessionUtils {
    public static boolean isCheckNum(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        if (session==null){
            return false;
        }
        String check_number_key=(String)session.getAttribute("CHECK_NUMBER_KEY");
        if (StringUtils.isBlank(check_number_key)){
            return false;
        }
        String saved=request.getParameter("checkNum");
        if (StringUtils.isBlank(saved)){
            return false;
        }
        return check_number_key.equalsIgnoreCase(saved);
    }
/*
* 保存用户当前登录信息到session中*/
    public static void setSysUserToSession(HttpServletRequest request, SysUser sysUser) {
        HttpSession session =request.getSession();
        if (sysUser==null){
            return;
        }
        session.setAttribute("sysUserKey",sysUser);
    }

    public static SysUser getSysUserFromSession(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        if (session==null){
            return  null;
        }
        SysUser sysUser=(SysUser)session.getAttribute("sysUserKey");
        return sysUser;
    }
}
