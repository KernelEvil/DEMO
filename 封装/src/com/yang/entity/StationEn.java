package com.yang.entity;

import java.util.List;

public class StationEn {
    public StationEn(){}
    public StationEn(String stationNo, String stationName, List<DevEn> devEnList) {
        this.stationNo = stationNo;
        this.stationName = stationName;
        this.devEnList = devEnList;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<DevEn> getDevEnList() {
        return devEnList;
    }

    public void setDevEnList(List<DevEn> devEnList) {
        this.devEnList = devEnList;
    }

    private String stationNo;
    private String stationName;
    private List<DevEn> devEnList;

}
