package com.zucc.smartfurniture.module.control.model.rec;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/3$ 15:26$
 * <p/>
 */
public class ControlItemRec {
    /**设备id*/
    private String device_id;
    /**场景描述*/
    private String classify;
    /**细节备注*/
    private String detail;
    /**开关状态 1-开 0-关*/
    private String state;

    public String getDevice_id() {
        return device_id;
    }

    public String getClassify() {
        return classify;
    }

    public String getDetail() {
        return detail;
    }

    public String getState() {
        return state;
    }
}
