package com.me.crm.domain;

/**
 * Created by DJ on 2017/4/24.
 */
public class SysPopedom implements java.io.Serializable {
    private SysPopedomId id;//主键

    public SysPopedomId getId() {
        return id;
    }

    public void setId(SysPopedomId id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopedomName() {
        return popedomName;
    }

    public void setPopedomName(String popedomName) {
        this.popedomName = popedomName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private Integer sort;
    private String title;
    private String popedomName;
    private String remark;

}
