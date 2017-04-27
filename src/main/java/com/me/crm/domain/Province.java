package com.me.crm.domain;

/**
 * Created by DJ on 2017/4/27.
 */
public class Province implements java.io.Serializable {
    private Integer id;
    private String name;
    private String pycode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPycode() {
        return pycode;
    }

    public void setPycode(String pycode) {
        this.pycode = pycode;
    }
}
