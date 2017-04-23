package com.me.crm.service;

import com.me.bean.SysUserSearch;
import com.me.crm.domain.SysUser;

import java.util.List;

/**
 * Created by DJ on 2017/4/23.
 */
public interface ISysUserService {
     public  final  static  String SERVICE_NAME = "com.me.crm.service.impl.SysUserServiceImpl";
/*
* 通过用户名和密码查询用户信息*/
    SysUser findSysUserByNameAnPassword(String name, String password);

    void saveSysUser(SysUser sysUser);

    List<SysUser> findSysUserByCondition(SysUserSearch sysUserSearch);

    void deleteSysUserById(Integer[] ids);

    void enableSysUsersByIds(Integer[] ids);

    void disableSysUsersByIds(Integer[] ids);

    SysUser findSysUserById(Integer id);

    void updateSysUser(SysUser newSysUser);
}
