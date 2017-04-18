package com.me.crm.service.impl;

import com.me.crm.dao.ISysRoleDao;
import com.me.crm.domain.SysRole;
import com.me.crm.service.ISysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by DJ on 2017/4/18.
 */
@Transactional(readOnly = true)
@Service(ISysRoleService.SERVICE_NAME)
public class SysRoleServiceImpl implements ISysRoleService {
    @Resource(name = ISysRoleDao.SERVICE_NAME)
    private ISysRoleDao sysRoleDao;
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveSysRole(SysRole sysRole) {
        sysRoleDao.save(sysRole);

    }
}
