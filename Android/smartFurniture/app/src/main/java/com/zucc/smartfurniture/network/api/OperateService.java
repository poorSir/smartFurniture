package com.zucc.smartfurniture.network.api;

import com.zucc.smartfurniture.module.control.model.rec.ControlItemRec;
import com.zucc.smartfurniture.module.record.model.rec.RecordRec;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.ListData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/3$ 15:16$
 * <p/>
 */
public interface OperateService {
    /**获取控制列表*/
    @POST("operate/device/scene")
    Observable<HttpResult<ListData<ControlItemRec>>> getControlList();

    /**绑定设备场景*/
    @FormUrlEncoded
    @POST("operate/device/bind")
    Observable<HttpResult> bindDeviceScene(@Field("deviceCode")String deviceCode,@Field("devicePassword") String devicePassword,@Field("classify")String classify,@Field("detail") String detail);

    /**获取设备操作信息*/
    @POST("operate/device/record")
    Observable<HttpResult<ListData<RecordRec>>> getRecordList();

    /**更改设备状态*/
    @FormUrlEncoded
    @POST("operate/device/control")
    Observable<HttpResult> controlState(@Field("deviceId") String deviceId,@Field("state") String state);

    /**删除设备绑定*/
    @FormUrlEncoded
    @POST("operate/device/delete")
    Observable<HttpResult> deleteBind(@Field("deviceId") String deviceId);

    /**修改设备信息*/
    @FormUrlEncoded
    @POST("operate/device/updata")
    Observable<HttpResult> updataDeviceInfo(@Field("deviceId") String deviceId,@Field("oldDevicePassword") String oldPassword,@Field("newDevicePassword") String newPassword,
                                            @Field("useScene") String useScene,@Field("detail") String detail);
    /**语音控制*/
    @FormUrlEncoded
    @POST("operate/device/voiceControl")
    Observable<HttpResult> voiceControl(@Field("message") String message);
}
