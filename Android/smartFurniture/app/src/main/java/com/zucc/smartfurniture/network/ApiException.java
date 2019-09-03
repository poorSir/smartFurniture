package com.zucc.smartfurniture.network;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/26$ 10:00$
 * Description:HTTP请求异常类
 * <p/>
 */
public class ApiException extends RuntimeException{
    private HttpResult result;
    public ApiException(HttpResult result) {
        this.result = result;
    }
    public HttpResult getResult() {
        return result;
    }

    public void setResult(HttpResult result) {
        this.result = result;
    }
}
