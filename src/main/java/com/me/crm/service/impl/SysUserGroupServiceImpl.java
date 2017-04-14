package com.me.crm.service.impl;

import com.me.crm.dao.ISysUserGroupDao;
import com.me.crm.domain.SysUserGroup;
import com.me.crm.service.ISysUserGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by DJ on 2017/4/10.
 */
@Transactional(readOnly = true)
@Service(ISysUserGroupService.SERVICE_NAME)
public class SysUserGroupServiceImpl implements ISysUserGroupService {
    @Resource(name = ISysUserGroupDao.SERVICE_NAME)
    private ISysUserGroupDao sysUserGroupDao;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveSysUserGroup(SysUserGroup sysUserGroup) {
        sysUserGroupDao.save(sysUserGroup);

    }

    public List<SysUserGroup> findSysUserGroups(String name, String principal) {

        name = "企业应用部";
        principal = "段捷";
        String whereHql = "";
        List paramsList = new ArrayList();
        if (org.apache.commons.lang.StringUtils.isNotBlank(name)) {
            whereHql = "and o.name LIKE ? ";
            paramsList.add("%" + name + "%");
        }
        if (StringUtils.isNotBlank(principal)) {
            whereHql = whereHql + "and o.principal = ?";
            paramsList.add(principal);
        }
        Object[] params = paramsList.toArray();
        System.out.println("whereHql  " + whereHql);
        //组织排序
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("o.id", "asc");
        orderby.put("o.name", "desc");
        List<SysUserGroup> list = sysUserGroupDao.findObjectsByConditionWithNoPage(whereHql, params, orderby);
        return list;
    }

}
