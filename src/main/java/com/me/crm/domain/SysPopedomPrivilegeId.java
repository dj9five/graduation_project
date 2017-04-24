package com.me.crm.domain;

/**
 * Created by DJ on 2017/4/24.
 */
public class SysPopedomPrivilegeId  implements  java.io.Serializable{
    private String roleId;
    private  String popedomModule;
    private String  popedomPrivilege;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysPopedomPrivilegeId that = (SysPopedomPrivilegeId) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (popedomModule != null ? !popedomModule.equals(that.popedomModule) : that.popedomModule != null)
            return false;
        return popedomPrivilege != null ? popedomPrivilege.equals(that.popedomPrivilege) : that.popedomPrivilege == null;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (popedomModule != null ? popedomModule.hashCode() : 0);
        result = 31 * result + (popedomPrivilege != null ? popedomPrivilege.hashCode() : 0);
        return result;
    }

    public String getRoleId() {

        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
