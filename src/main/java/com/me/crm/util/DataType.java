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

    /*
    * 利用给定的流水位生成第一个流水号*/
    public static String geneFirstGlideNumber(Integer glideBit) {
        if (glideBit == null || glideBit < 3) {
            glideBit = 3;
        }
        String glideNumber = "";
        for (int i = 0; i < glideBit - 1; i++) {
            glideNumber = glideNumber + "0";
        }
        glideNumber = glideNumber + "1";
        return glideNumber;
    }

    public static void main(String[] args) {
        System.err.println(geneNextGlideNumber("005"));
    }

    /*
    * 根据当前的流水号生成下一个流水号*/
    public static String geneNextGlideNumber(String curGlideNumber) {
        if (StringUtils.isBlank(curGlideNumber)) {
            throw new RuntimeException("不能计算下一个流水号");
        }
        curGlideNumber="1"+curGlideNumber;
        Integer icurGlideNumber=Integer.parseInt(curGlideNumber);
        icurGlideNumber++;
        curGlideNumber=icurGlideNumber+"";
        curGlideNumber=curGlideNumber.substring(1);
        return curGlideNumber;
    }
}
