package com.luoyi.bean;

import java.util.Date;

public class AlarmLog {
    private Integer alarmId;

    private String androidId;

    private String alarmImgPath;

    private String time;

    private Integer type;

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId == null ? null : androidId.trim();
    }

    public String getAlarmImgPath() {
        return alarmImgPath;
    }

    public void setAlarmImgPath(String alarmImgPath) {
        this.alarmImgPath = alarmImgPath == null ? null : alarmImgPath.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}