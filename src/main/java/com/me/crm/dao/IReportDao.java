package com.me.crm.dao;

import com.me.bean.ReportBean;

import java.util.List;

/**
 * Created by DJ on 2017/5/14.
 */
public interface IReportDao extends ICommonDao<ReportBean> {
    public final static String  SERVICE_NAME="com.me.crm.dao.impl.ReportDaoImpl";

    List<ReportBean> findReportBeans();
}
