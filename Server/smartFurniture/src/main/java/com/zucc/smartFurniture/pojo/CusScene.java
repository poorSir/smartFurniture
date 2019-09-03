package com.zucc.smartFurniture.pojo;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/7 10:49
 * Description:定制场景表
 **/
public class CusScene {
    private int id;
    private int cus_id;
    private int user_id;
    private int device_id;
    private String operation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
