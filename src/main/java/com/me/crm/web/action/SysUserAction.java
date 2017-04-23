package com.me.crm.web.action;

import com.me.bean.SysUserSearch;
import com.me.crm.container.ServiceProvider;
import com.me.crm.domain.SysRole;
import com.me.crm.domain.SysUser;
import com.me.crm.domain.SysUserGroup;
import com.me.crm.service.ISysRoleService;
import com.me.crm.service.ISysUserGroupService;
import com.me.crm.service.ISysUserService;
import com.me.crm.util.DataType;
import com.me.crm.util.MD5keyBean;
import com.me.crm.util.SQLDateConverter;
import com.me.crm.util.SessionUtils;
import com.me.crm.web.form.SysUserForm;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by DJ on 2017/4/21.
 */
public class SysUserAction extends BaseAction implements ModelDriven<SysUserForm> {
    private SysUserForm sysUserForm = new SysUserForm();
    private static final long serialVersionUID = 1L;
    private ISysUserService sysUserService =
            (ISysUserService) ServiceProvider.getService(ISysUserService.SERVICE_NAME);
    //获取部门业务层对象
    private ISysUserGroupService sysUserGroupService =
            (ISysUserGroupService) ServiceProvider.getService(ISysUserGroupService.SERVICE_NAME);
    //获取权限组的业务层对象
    private ISysRoleService sysRoleService =
            (ISysRoleService) ServiceProvider.getService(ISysRoleService.SERVICE_NAME);

    /*
    * 显示用户查询页面
    * */
    public String list() throws InvocationTargetException, IllegalAccessException {
        //处理所选部门的下拉框
        List<SysUserGroup> sysUserGroupSelect = sysUserGroupService.findAllSysUserGroup();
        request.setAttribute("sysUserGroupSelect", sysUserGroupSelect);
        //封装查询条件
        SysUserSearch sysUserSearch = new SysUserSearch();
        BeanUtils.copyProperties(sysUserSearch, sysUserForm);
        List<SysUser> sysUserList = sysUserService.findSysUserByCondition(sysUserSearch);
        request.setAttribute("sysUserList", sysUserList);
        return "list";
    }

    //删除
    public String delete() {
        String[] sids = request.getParameterValues("ids");
        Integer ids[] = DataType.converterStringArray2IntegerArray(sids);
        if (ids != null && ids.length > 0) {
            sysUserService.deleteSysUserById(ids);
        }
        return "listAction";
    }

    /*
    * 启用*/
    public String enable() {
        String[] sids = request.getParameterValues("ids");
        Integer ids[] = DataType.converterStringArray2IntegerArray(sids);
        if (ids != null && ids.length > 0) {
            sysUserService.enableSysUsersByIds(ids);
        }
        return "listAction";

    }

    /*停用*/
    public String disable() {
        String[] sids = request.getParameterValues("ids");
        Integer ids[] = DataType.converterStringArray2IntegerArray(sids);
        if (ids != null && ids.length > 0) {
            sysUserService.disableSysUsersByIds(ids);
        }
        return "listAction";

    }

    //编辑
    public String edit() throws InvocationTargetException, IllegalAccessException {
        //处理权限组下拉选
        List<SysRole> sysRoleSelect = sysRoleService.findAllSysRole();
        request.setAttribute("sysRoleSelect", sysRoleSelect);
        //处理部门的下拉选
        List<SysUserGroup> sysUserGroupSelect = sysUserGroupService.findAllSysUserGroup();
        request.setAttribute("sysUserGroupSelect", sysUserGroupSelect);
        String sid = request.getParameter("id");
        if (StringUtils.isNotBlank(sid)) {
            Integer id = Integer.parseInt(sid.trim());
            SysUser oldSysUser = sysUserService.findSysUserById(id);
            BeanUtils.copyProperties(sysUserForm, oldSysUser);
            //处理权限的下拉回显
            if (oldSysUser.getSysRole() != null) {
                sysUserForm.setRoleId(oldSysUser.getSysRole().getId());
            }
            //处理部门的下拉回显
            if (oldSysUser.getSysUserGroup() != null) {
                sysUserForm.setGroupId(oldSysUser.getSysRole().getId());
                return "edit";
            }
        }
            return null;
        }

    /*
    * 显示用户添加页面
    * */

    public String add() {
        //处理权限组下拉选
        List<SysRole> sysRoleSelect = sysRoleService.findAllSysRole();
        request.setAttribute("sysRoleSelect", sysRoleSelect);
        //处理部门的下拉选
        List<SysUserGroup> sysUserGroupSelect = sysUserGroupService.findAllSysUserGroup();
        request.setAttribute("sysUserGroupSelect", sysUserGroupSelect);

        //处理创建人 修改人 创建时间 修改时间
        //获取当前登录用户
        SysUser sysUser = SessionUtils.getSysUserFromSession(request);
        if (sysUser == null) {
            return "login";
        }
        //设置创建人和修改人
        sysUserForm.setCreator(sysUser.getCnname());
        sysUserForm.setUpdater(sysUser.getCnname());

        String curDate = DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss");
        sysUserForm.setCreateTime(curDate);
        sysUserForm.setUpdateTime(curDate);
        return "add";
    }
    public String update() throws InvocationTargetException, IllegalAccessException {
        SysUser newSysUser = new SysUser();
        //注册转换器
        ConvertUtils.register(new SQLDateConverter(), java.sql.Date.class);
        BeanUtils.copyProperties(newSysUser, sysUserForm);
        SysRole sysRole = new SysRole();
        sysRole.setId(sysUserForm.getRoleId());
        newSysUser.setSysRole(sysRole);

        SysUserGroup sysUserGroup = new SysUserGroup();
        if (StringUtils.isNotBlank(sysUserForm.getGroupId())) {
            sysUserGroup.setId(Integer.parseInt(sysUserForm.getGroupId()));
        }
        newSysUser.setSysUserGroup(sysUserGroup);
        //获取当前用户
        SysUser curSysUser=SessionUtils.getSysUserFromSession(request);
        if (curSysUser==null){
            return "login";
        }
        //设置修改者和修改时间
        newSysUser.setUpdater(curSysUser.getCnname());
        newSysUser.setUpdateTime(DateFormatUtils.format(new java.util.Date(),"yyyy-MM-dd HH:mm:ss"));
        sysUserService.updateSysUser(newSysUser);
        return "listAction";
    }

    /*
    * 人事保存*/
    public String save() throws InvocationTargetException, IllegalAccessException {
        SysUser sysUser = new SysUser();
        //注册转换器
        ConvertUtils.register(new SQLDateConverter(), java.sql.Date.class);
        BeanUtils.copyProperties(sysUser, sysUserForm);
        MD5keyBean m=new MD5keyBean();
        String password=m.getkeyBeanofStr(sysUserForm.getPassword());
        sysUser.setPassword(password);
        SysRole sysRole = new SysRole();
        sysRole.setId(sysUserForm.getRoleId());
        sysUser.setSysRole(sysRole);

        SysUserGroup sysUserGroup = new SysUserGroup();
        if (StringUtils.isNotBlank(sysUserForm.getGroupId())) {
            sysUserGroup.setId(Integer.parseInt(sysUserForm.getGroupId()));
        }
        sysUser.setSysUserGroup(sysUserGroup);
        sysUserService.saveSysUser(sysUser);
        return "listAction";
    }

    //验证用户登录
    public String isLogin() throws UnsupportedEncodingException {
        //处理验证码
        boolean flag = SessionUtils.isCheckNum(request);
        if (!flag) {
            this.addFieldError("checkNum", "验证码错误");
            return "login";
        }
        //处理用户名 密码输入是否正确
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        MD5keyBean m = new MD5keyBean();
        password = m.getkeyBeanofStr("123456");
        SysUser sysUser = sysUserService.findSysUserByNameAnPassword(name, password);
        if (sysUser == null) {
            this.addFieldError("name", "用户名或者密码错误");
            return "login";
        }
        //登陆成功，放置当前对象到session中
        SessionUtils.setSysUserToSession(request, sysUser);
        //处理cookies
        addCookie(name, request.getParameter("password"), request, response);
        return "main";
    }

    private void addCookie(String name, String password, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)) {
            //创建cookie
            Cookie nameCookie = new Cookie("name", java.net.URLEncoder.encode(name, "UTF-8"));
            Cookie pswCookie = new Cookie("psw", password);
            //设置cookie的父路径
            nameCookie.setPath(request.getContextPath() + "/");
            pswCookie.setPath(request.getContextPath() + "/");
            //获取是否保存cookie
            String rememberMe = request.getParameter("rememberMe");
            if (rememberMe == null) {
                nameCookie.setMaxAge(0);
                pswCookie.setMaxAge(0);
            } else {
                nameCookie.setMaxAge(7 * 24 * 60 * 60);
                pswCookie.setMaxAge(7 * 24 * 60 * 60);
            }
            //加入cookie到响应头
            response.addCookie(nameCookie);
            response.addCookie(pswCookie);
        }
    }

    public SysUserForm getModel() {
        return sysUserForm;
    }
}
