package com.zucc.smartFurniture.pojo;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/7 10:29
 * Description:设备表
 **/
public class Device {
    private int id;
    private String code;
    private String password;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
