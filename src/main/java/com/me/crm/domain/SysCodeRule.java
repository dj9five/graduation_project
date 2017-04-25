package com.me.crm.domain;

/**
 * Created by DJ on 2017/4/26.
 */
public class SysCodeRule implements java.io.Serializable {
    private Integer id;
    private String module;
    private String areaPrefix;
    private String areaTime;
    private Integer glideBit;
    private String currentCode;
    private String tabName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAreaPrefix() {
        return areaPrefix;
    }

    public void setAreaPrefix(String areaPrefix) {
        this.areaPrefix = areaPrefix;
    }

    public String getAreaTime() {
        return areaTime;
    }

    public void setAreaTime(String areaTime) {
        this.areaTime = areaTime;
    }

    public Integer getGlideBit() {
        return glideBit;
    }

    public void setGlideBit(Integer glideBit) {
        this.glideBit = glideBit;
    }

    public String getCurrentCode() {
        return currentCode;
    }

    public void setCurrentCode(String currentCode) {
        this.currentCode = currentCode;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getNextseq() {
        return nextseq;
    }

    public void setNextseq(String nextseq) {
        this.nextseq = nextseq;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    private String available;
    private String nextseq;
    private String curDate;

    public SysCodeRule() {
    }

}
