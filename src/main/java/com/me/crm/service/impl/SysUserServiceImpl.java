package com.me.crm.service.impl;

import com.me.bean.SysUserSearch;
import com.me.crm.dao.ISysUserDao;
import com.me.crm.domain.SysUser;
import com.me.crm.service.ISysUserService;
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
 * Created by DJ on 2017/4/23.
 */
@Service(ISysUserService.SERVICE_NAME)
public class SysUserServiceImpl implements ISysUserService {
    @Resource(name = ISysUserDao.SERVICE_NAME)
    private ISysUserDao sysUserDao;

    public SysUser findSysUserByNameAnPassword(String name, String password) {
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)) {
            String whereHql = " and o.name= ? and o.password=?";
            Object[] params = {name, password};
            List<SysUser> list = sysUserDao.findObjectsByConditionWithNoPage(whereHql, params);
            if (list != null && list.size() == 1) {
                return list.get(0);
            }
        }
        return null;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveSysUser(SysUser sysUser) {
        sysUserDao.save(sysUser);

    }

    public List<SysUser> findSysUserByCondition(SysUserSearch sysUserSearch) {
        if(sysUserSearch==null){
            throw new RuntimeException("传递给业务层用户查询条件的对象为空");
        }

        String whereHql="";
        List paramsList=new ArrayList();

        if(StringUtils.isNotBlank(sysUserSearch.getName())){
            whereHql=whereHql+" and  o.name like ?";
            paramsList.add("%"+sysUserSearch.getName().trim()+"%");
        }

        if(StringUtils.isNotBlank(sysUserSearch.getCnname())){
            whereHql=whereHql+" and  o.cnname like ?";
            paramsList.add("%"+sysUserSearch.getCnname().trim()+"%");
        }

        if(StringUtils.isNotBlank(sysUserSearch.getStatus())){
            whereHql=whereHql+" and  o.status = ?";
            paramsList.add(sysUserSearch.getStatus().trim());
        }

        if(sysUserSearch.getGroupId()!=null&&sysUserSearch.getGroupId()!=0){
            whereHql=whereHql+" and  o.sysUserGroup.id = ?";
            paramsList.add(sysUserSearch.getGroupId());
        }
        Object[] params=paramsList.toArray();
        LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
        orderby.put("o.id", "asc");

        return sysUserDao.findObjectsByConditionWithNoPage(whereHql, params, orderby);
    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSysUserById(Integer[] ids) {
        sysUserDao.deleteById(ids);

    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void enableSysUsersByIds(Integer[] ids) {
        if (ids!=null&&ids.length>0){
            for (int i=0;i<ids.length;i++){
                SysUser sysUser=sysUserDao.findObjectById(ids[i]);
                sysUser.setStatus("Y");
                sysUserDao.update(sysUser);

            }
        }
    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void disableSysUsersByIds(Integer[] ids) {
        if (ids!=null&&ids.length>0){
            for (int i=0;i<ids.length;i++){
                SysUser sysUser=sysUserDao.findObjectById(ids[i]);
                sysUser.setStatus("N");
                sysUserDao.update(sysUser);

            }
        }
    }

    public SysUser findSysUserById(Integer id) {
        return sysUserDao.findObjectById(id);
    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSysUser(SysUser newSysUser) {
        sysUserDao.update(newSysUser);
    }
}

