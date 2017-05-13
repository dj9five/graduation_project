package com.me.crm.dao.impl;

import com.me.bean.ReportBean;
import com.me.crm.dao.IReportDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by DJ on 2017/5/14.
 */
@Repository(IReportDao.SERVICE_NAME)
public class ReportDaoImpl extends CommonDaoImpl<ReportBean> implements IReportDao {
    public List<ReportBean> findReportBeans() {
        List<ReportBean> list = (List<ReportBean>) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                String hql = "select new com.me.bean.ReportBean(o.grade,count(*)) from Company o group by  o.grade";
                Query query = session.createQuery(hql);
                List list = query.list();
                return list;
            }
        });

        return list;
    }
}
