package com.me.crm.domain;






/**
 * Created by DJ on 2017/3/27.
 */


public class SysUserGroup {
 /*
 `id` INTEGER(11) NOT NULL AUTO_INCREMENT,               #编号
  `remark` TEXT,                                          #备注
  `name` VARCHAR(100) DEFAULT NULL,                       #部门名称
  `principal` VARCHAR(50)  DEFAULT NULL,                  #部门负责人
  `incumbent` VARCHAR(200)  DEFAULT NULL,                 #部门职能
  */
 private Integer id;//主键
 private String remark;//备注

//部门名称
    private String name;
    private  String principal;//部门负责人
 private String incumbent;//部门职能
//Alt+insert生成get set方法；
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
