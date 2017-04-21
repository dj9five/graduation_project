package com.me.crm.web.action;

import com.me.crm.util.SessionUtils;

/**
 * Created by DJ on 2017/4/21.
 */
public class SysUserAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    public String list() {
        return "list";
    }

    public String add() {
        return "add";
    }

    //验证用户登录
    public String isLogin() {
        //处理验证码
boolean flag= SessionUtils.isCheckNum(request);
if (!flag){
    this.addFieldError("checkNum","验证码错误");
    return "login";
}
        //处理用户名 密码输入是否正确

        //处理cookies
        return "main";
    }
}
