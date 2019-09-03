package com.zucc.smartFurniture.pojo.sub;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/7 17:16
 * Description:
 **/
public class RecordSub {
    private String deviceId;
    private String type;
    private String state;
    private String time;
    private String useScene;
    private String detail;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUseScene() {
        return useScene;
    }

    public void setUseScene(String useScene) {
        this.useScene = useScene;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
