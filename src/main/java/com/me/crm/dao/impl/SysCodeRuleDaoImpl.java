package com.me.crm.dao.impl;

import com.me.crm.dao.ISysCodeRuleDao;
import com.me.crm.domain.SysCodeRule;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/26.
 */
@Repository(ISysCodeRuleDao.service_name)
public class SysCodeRuleDaoImpl extends CommonDaoImpl<SysCodeRule> implements ISysCodeRuleDao {
}
