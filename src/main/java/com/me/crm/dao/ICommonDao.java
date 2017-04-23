package com.me.crm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by DJ on 2017/3/30.
 */
public interface ICommonDao<T> {
    void save(T entity);

    void update(T entity);

    T findObjectById(Serializable id);

    void deleteById(Serializable... ids);

    void deleteALLObject(Collection<T> entities);

    List<T> findObjectsByConditionWithNoPage(String whereHql, Object[] params, LinkedHashMap<String, String> orderby);

    List<T> findObjectsByConditionWithNoPage(String whereHql, Object[] name);

    List<T> findObjectsByConditionWithNoPage();

    List<T> findObjectsByConditionWithNoPage(LinkedHashMap<String, String> orderby);
}
