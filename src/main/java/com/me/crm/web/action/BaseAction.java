package com.me.crm.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DJ on 2017/4/18.
 */
public class BaseAction extends ActionSupport implements ServletRequestAware {
    protected HttpServletRequest request;
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
