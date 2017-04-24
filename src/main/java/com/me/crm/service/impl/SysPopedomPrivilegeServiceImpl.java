package com.me.crm.service.impl;

import com.me.crm.dao.ISysPopedomPrivilegeDao;
import com.me.crm.domain.SysPopedomPrivilege;
import com.me.crm.domain.SysPopedomPrivilegeId;
import com.me.crm.service.ISysPopedomPrivilegeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by DJ on 2017/4/25.
 */
@Transactional(readOnly = true)
@Service(ISysPopedomPrivilegeService.SERVICE_NAME)
public class SysPopedomPrivilegeServiceImpl implements ISysPopedomPrivilegeService {
    @Resource(name = ISysPopedomPrivilegeDao.SERVICE_NAME)
    private ISysPopedomPrivilegeDao sysPopedomPrivilegeDao;
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updatePopedom(String roleId, String[] popedomModules) {
       //删除该权限组对应的所有权限
        if (StringUtils.isNotBlank(roleId)&&popedomModules!=null&&popedomModules.length>0){
            String whereHql="and o.id.roleId=?";
            Object[] params={roleId};
            List<SysPopedomPrivilege> list=sysPopedomPrivilegeDao.findObjectsByConditionWithNoPage(whereHql,params);
            sysPopedomPrivilegeDao.deleteALLObject(list);
        }
        //保存权限组对应的权限
        if (StringUtils.isNotBlank(roleId)&&popedomModules!=null&&popedomModules.length>0){
              for (int i=0;i<popedomModules.length;i++){
                  if (StringUtils.isNotBlank(popedomModules[i])){
                      String[] str=popedomModules[i].split(",");
                      SysPopedomPrivilege sysPopedomPrivilege=new SysPopedomPrivilege();
                      SysPopedomPrivilegeId id=new SysPopedomPrivilegeId();
                      id.setRoleId(roleId);
                      id.setPopedomModule(str[0]);
                      id.setPopedomPrivilege(str[1]);
                      sysPopedomPrivilege.setId(id);
                      sysPopedomPrivilegeDao.save(sysPopedomPrivilege);
                  }
              }
        }

    }
}
