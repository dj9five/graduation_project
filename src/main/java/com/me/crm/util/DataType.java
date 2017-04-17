package com.me.crm.util;

import org.apache.commons.lang.StringUtils;

/**
 * 该方法将字符串数组转化为整形的数组
 * Created by DJ on 2017/4/17.
 */
public class DataType {
    public static Integer[] converterStringArray2IntegerArray(String[] sids) {
        if (sids != null && sids.length > 0) {
            Integer[] ids = new Integer[sids.length];
            for (int i = 0; i < sids.length; i++) {
                if (StringUtils.isNotBlank(sids[i])) {
                    ids[i] = Integer.parseInt(sids[i]);
                }
            }
            return ids;
        }
        return null;
    }
}
