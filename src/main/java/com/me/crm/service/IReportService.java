package com.me.crm.service;

import com.me.bean.ReportBean;

import java.util.List;

/**
 * Created by DJ on 2017/5/14.
 */
public interface IReportService {
    String SERVICE_NAME = "com.me.crm.service.impl.ReportServiceImpl";

    List<ReportBean> findReportBeans();
}
