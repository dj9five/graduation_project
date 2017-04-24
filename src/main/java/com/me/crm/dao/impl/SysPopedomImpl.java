package com.me.crm.dao.impl;

import com.me.crm.dao.ISysPopedomDao;
import com.me.crm.domain.SysPopedom;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/24.
 */
@Repository(ISysPopedomDao.service_name)
public class SysPopedomImpl extends CommonDaoImpl<SysPopedom> implements ISysPopedomDao {
}
