package com.zucc.smartFurniture.pojo;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/7 10:42
 * Description:设备场景
 **/
public class DeviceScene {
    private int id;
    private int user_id;
    private int device_id;
    private String classify;
    private String detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
