package com.zucc.smartFurniture.pojo.sub;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/12 17:43
 * Description:
 **/
public class SceneSub {
    private int device_id;
    private String classify;
    private String detail;
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
