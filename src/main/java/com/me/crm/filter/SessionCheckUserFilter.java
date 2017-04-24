package com.me.crm.filter;

import com.me.crm.domain.SysUser;
import com.me.crm.util.SessionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DJ on 2017/4/24.
 */
public class SessionCheckUserFilter implements Filter {
    private List<String> list;

    public void init(FilterConfig filterConfig) throws ServletException {
        list=new ArrayList<String>();
        list.add("/image.jsp");
        list.add("/index.jsp");
        list.add("/WEB-INF/sys/login.jsp");
        list.add("/sys/sysUserAction_isLogin.do");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        String path=request1.getServletPath();
        //对登录路径，验证码路径放行
        if (list!=null&&list.contains(path)){
            chain.doFilter(request1,response1);
            return;
        }
        //获取当前登录的用户
        SysUser sysUser= SessionUtils.getSysUserFromSession(request1);
        //如果用户不为空，表示用户已经登陆
        if (sysUser!=null){
            chain.doFilter(request1,response1);
        }
        //如果过用户为空，则重定向到login.jsp
        else{
            response1.sendRedirect(request1.getContextPath());
        }
    }

    public void destroy() {

    }
}
