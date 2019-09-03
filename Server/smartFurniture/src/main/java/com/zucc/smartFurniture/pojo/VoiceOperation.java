package com.zucc.smartFurniture.pojo;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-04-05 14:47
 * Description:
 **/
public class VoiceOperation {
    private int operate;//0 关闭 1打开
    private int deviceId;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }
}
