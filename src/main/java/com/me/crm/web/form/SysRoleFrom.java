package com.me.crm.web.form;

/**
 * Created by DJ on 2017/4/18.
 */
public class SysRoleFrom implements java.io.Serializable {
    private String id;
    private String name;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
