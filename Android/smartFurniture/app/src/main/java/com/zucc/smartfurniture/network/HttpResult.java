package com.zucc.smartfurniture.network;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/25$ 16:46$
 * <p/>
 */
public class HttpResult<T> {
    /** 错误码 */
    private String code;
    /**错误信息*/
    private String msg;
    /**返回消息主题*/
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
