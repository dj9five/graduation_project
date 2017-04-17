package com.me.crm.service.impl;

import com.me.bean.SysUserGroupSearch;
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

    public List<SysUserGroup> findSysUserGroups(SysUserGroupSearch sysUserGroupSearch) {
        if (sysUserGroupSearch==null)
        {
            throw new RuntimeException("查询部门对象为空");
        }
        //组织查询条件
        String whereHql = "";
        //定义封装查询条件的List
        List paramList = new ArrayList();
        if (StringUtils.isNotBlank(sysUserGroupSearch.getName())){
            whereHql=" and o.name like ?";
            paramList.add("%"+sysUserGroupSearch.getName().trim()+"%");
        }
        Object[] params = paramList.toArray();
        //排序
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("o.id","asc");
        return sysUserGroupDao.findObjectsByConditionWithNoPage(whereHql, params, orderby);
    }

    public SysUserGroup findSysUserGroupById(Integer id) {

        return sysUserGroupDao.findObjectById(id);
    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSysUserGroup(SysUserGroup sysUserGroup) {
        sysUserGroupDao.update(sysUserGroup);

    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSysUserGroupByIds(Integer[] ids) {
        sysUserGroupDao.deleteById(ids);
    }


}
