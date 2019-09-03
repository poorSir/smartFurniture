package com.zucc.smartfurniture.network;

import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.utils.SharePreferenceInfo;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/25$ 15:14$
 * Description:添加静态参数
 * <p/>
 */
public class BaseParamsInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request newRequest = addParam(oldRequest);
        return chain.proceed(newRequest);
    }
    /**添加参数*/
    public Request addParam(Request oldRequest){
        String token = (String) SharePreferenceInfo.getInstance().getValue(BaseParam.token,SharePreferenceInfo.DataType.STRING);
        String userId =(String) SharePreferenceInfo.getInstance().getValue(BaseParam.userId,SharePreferenceInfo.DataType.STRING);
        if(token == null){
            token ="";
        }
        if(userId == null){
            userId="";
        }
        RequestBody newFormBody = new FormBody.Builder()
                .add("token",token)
                .add("userId",userId)
                .build();
        //默认添加formBody后不能添加新的form表单，需要先将RequestBody转成string去拼接
        String postBodyString = bodyToString(oldRequest.body());
        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(newFormBody);
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(),oldRequest.body())
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8"), postBodyString))
                .build();
        return newRequest;
    }
    /**RequestBody转String的方法*/
    private static String bodyToString(final RequestBody request){
        try {
            final RequestBody copy   = request;
            final Buffer      buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
}
