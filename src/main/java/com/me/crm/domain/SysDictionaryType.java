package com.me.crm.domain;

/**
 * Created by DJ on 2017/4/27.
 */
public class SysDictionaryType implements java.io.Serializable {
    private Integer id;
    private Integer sort;
    private  String remark;
    private  String code;
    private  String value;
    private Character sysFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Character getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(Character sysFlag) {
        this.sysFlag = sysFlag;
    }
}
