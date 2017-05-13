package com.me.crm.service.impl;

import com.me.bean.ReportBean;
import com.me.crm.dao.IReportDao;
import com.me.crm.service.IReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by DJ on 2017/5/14.
 */
@Service(IReportService.SERVICE_NAME)
public class ReportServiceImpl implements IReportService {
    @Resource(name = IReportDao.SERVICE_NAME)
    private IReportDao reportDao;
    public List<ReportBean> findReportBeans() {
       List<ReportBean> list= reportDao.findReportBeans();
        return list;
    }
}
