package com.yang.entity;

public class CollectEn {

    public CollectEn(){}
    public CollectEn(String collectNo, String collectName, String collectValue, int collectFlag) {
        this.collectNo = collectNo;
        this.collectName = collectName;
        this.collectValue = collectValue;
        this.collectFlag = collectFlag;
    }

    public String getCollectNo() {
        return collectNo;
    }

    public void setCollectNo(String collectNo) {
        this.collectNo = collectNo;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public String getCollectValue() {
        return collectValue;
    }

    public void setCollectValue(String collectValue) {
        this.collectValue = collectValue;
    }

    public int getCollectFlag() {
        return collectFlag;
    }

    public void setCollectFlag(int collectFlag) {
        this.collectFlag = collectFlag;
    }

    private String collectNo;
    private String collectName;
    private String collectValue;
    private int collectFlag;

}
