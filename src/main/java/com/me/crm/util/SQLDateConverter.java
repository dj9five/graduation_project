package com.me.crm.util;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

/**
 * Created by DJ on 2017/4/23.
 */
public class SQLDateConverter implements Converter{
    public Object convert(Class type, Object value) {
        if (value==null){
            return null;
        }
        if (type==null){
            return null;
        }
        if (type!=java.sql.Date.class){
            return null;
        }
        if (value instanceof java.lang.String){
            String str=(String)value;
            if (StringUtils.isNotBlank(str)){
                return java.sql.Date.valueOf((String)value);
            }
        }
        return null;
    }
}
