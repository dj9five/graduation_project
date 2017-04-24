package com.me.crm.domain;

import java.io.Serializable;

/**
 * Created by DJ on 2017/4/24.
 */
public class SysPopedomId implements Serializable {
    private String popedomModule;
    private String popedomPrivilege;
    private String popedomName;
    private String remark;
    private int sort;
    private String title;
    private SysPopedomId id;

    public SysPopedomId getId() {
        return id;
    }

    public void setId(SysPopedomId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPopedomName() {
        return popedomName;
    }

    public void setPopedomName(String popedomName) {
        this.popedomName = popedomName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysPopedomId that = (SysPopedomId) o;

        if (!popedomModule.equals(that.popedomModule)) return false;
        return popedomPrivilege.equals(that.popedomPrivilege);
    }

    @Override
    public int hashCode() {
        int result = popedomModule.hashCode();
        result = 31 * result + popedomPrivilege.hashCode();
        return result;
    }

    public String getPopedomModule() {

        return popedomModule;
    }

    public void setPopedomModule(String popedomModule) {
        this.popedomModule = popedomModule;
    }

    public String getPopedomPrivilege() {
        return popedomPrivilege;
    }

    public void setPopedomPrivilege(String popedomPrivilege) {
        this.popedomPrivilege = popedomPrivilege;
    }
}
