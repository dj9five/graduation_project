package com.me.crm.dao.impl;

import com.me.crm.dao.ICommonDao;
import com.me.crm.util.GenericClass;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DJ on 2017/3/30.
 */
@SuppressWarnings("unchecked")
public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T> {
    private Class entityClass = GenericClass.getGenericClass(this.getClass());

    public void save(Object entity) {
        // getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().save(entity);
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactoryDI(SessionFactory sessionFactory) {
        //调用父类的setSessionFactory方法，注入sessionFactory
        super.setSessionFactory(sessionFactory);
    }

    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public T findObjectById(Serializable id) {
        if (id == null) {
            throw new RuntimeException("查找的[" + id + "]不能为空");
        }

        return (T) this.getHibernateTemplate().get(entityClass, id);
    }

    /*
    通过id批量删除
     */
    public void deleteById(Serializable... ids) {
        if (ids != null && ids.length > 0) {
            for (Serializable id : ids) {
                Object entity = this.getHibernateTemplate().get(entityClass, id);
                if (entity == null) {
                    throw new RuntimeException("查找的[" + id + "]不存在");
                }
                this.getHibernateTemplate().delete(entity);
            }
        }
    }

    public void deleteALLObject(Collection<T> entities) {
        this.getHibernateTemplate().deleteAll(entities);
    }

    public List<T> findObjectsByConditionWithNoPage(String whereHql, final Object[] params, LinkedHashMap<String, String> orderby) {
        String hql = "select  o  from " + entityClass.getSimpleName() + " o where 1=1 ";
        //在select语句后面加查询条件
        if (StringUtils.isNotBlank(whereHql)) {
            hql = hql + whereHql;
        }
        //处理排序
        String orderbystr = buildOderBy(orderby);
        hql = hql + orderbystr;
        final String fhql = hql;
        List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(fhql);
                setParams(query, params);
                return query.list();
            }
        });
        return list;
    }

    public List<T> findObjectsByConditionWithNoPage(String whereHql, Object[] params) {
        return this.findObjectsByConditionWithNoPage(whereHql, params, null);
    }

    public List<T> findObjectsByConditionWithNoPage() {
        return this.findObjectsByConditionWithNoPage(null, null, null);
    }

    public List<T> findObjectsByConditionWithNoPage(LinkedHashMap<String, String> orderby) {
        return this.findObjectsByConditionWithNoPage(null, null, orderby);
    }

    public void setParams(Query query, Object[] params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }

        }
    }

    public String buildOderBy(LinkedHashMap<String, String> orderby) {
        StringBuffer buf = new StringBuffer("");
        if (orderby != null && !orderby.isEmpty()) {
            buf.append("  order by  ");
            for (Map.Entry<String, String> em : orderby.entrySet()) {
                buf.append(em.getKey() + " " + em.getValue() + ",");
            }
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }
}

