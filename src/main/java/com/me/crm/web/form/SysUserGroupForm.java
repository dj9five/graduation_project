package com.me.crm.web.form;

/**
 * VO对象，保存jsp页面的值
 * Created by DJ on 2017/4/11.
 */
public class SysUserGroupForm implements java.io.Serializable {
    private String id;
    private String name;
    private String principal;
    private String incumbent;
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

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getIncumbent() {
        return incumbent;
    }

    public void setIncumbent(String incumbent) {
        this.incumbent = incumbent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}