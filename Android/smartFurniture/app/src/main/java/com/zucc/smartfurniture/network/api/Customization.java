package com.zucc.smartfurniture.network.api;

import com.zucc.smartfurniture.module.control.model.rec.ControlItemRec;
import com.zucc.smartfurniture.module.mine.model.rec.CustomizationDeviceRec;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.ListData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/15$ 15:42$
 * <p/>
 */
public interface Customization {
    /**获取定制列表场景*/
    @FormUrlEncoded
    @POST("operate/customization/cusScene")
    Observable<HttpResult<ListData<CustomizationDeviceRec>>> getCusSceneList(@Field("cusId") String cusId);

    /**获取所有设备列表*/
    @POST("operate/customization/getDeviceList")
    Observable<HttpResult<ListData<ControlItemRec>>> getDeviceList();

    /**增加定制场景设备*/
    @FormUrlEncoded
    @POST("operate/customization/cusSceneAdd")
    Observable<HttpResult> addCusScene(@Field("cusId") String cusId,@Field("deviceId") String deviceId,@Field("operation") String operation);

    /**更改定制场景操作*/
    @FormUrlEncoded
    @POST("operate/customization/cusSceneUpdata")
    Observable<HttpResult> updataCusScene(@Field("cusId") String cusId,@Field("deviceId") String deviceId,@Field("operation") String operation);

    /**执行场景*/
    @FormUrlEncoded
    @POST("operate/customization/execute")
    Observable<HttpResult> execute(@Field("cusId") String cusId);
}
