package com.yang.entity;

import java.util.List;

public class DevEn {
    public DevEn(){}
    public DevEn(String devNo, String devName, List<CollectEn> collectEnList) {
        this.devNo = devNo;
        this.devName = devName;
        this.collectEnList = collectEnList;
    }

    public String getDevNo() {
        return devNo;
    }

    public void setDevNo(String devNo) {
        this.devNo = devNo;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public List<CollectEn> getCollectEnList() {
        return collectEnList;
    }

    public void setCollectEnList(List<CollectEn> collectEnList) {
        this.collectEnList = collectEnList;
    }

    private String devNo;
    private String devName;
    private List<CollectEn> collectEnList;

}
