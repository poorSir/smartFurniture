package com.zucc.smartFurniture.service;

import com.zucc.smartFurniture.common.UserAccess;
import com.zucc.smartFurniture.pojo.DeviceScene;
import com.zucc.smartFurniture.pojo.HttpResult;

import java.util.List;

public interface DeviceService {
    /**通过userid获取设备场景列表*/
   HttpResult getScene(int userId);
   /**绑定设备场景*/
   HttpResult bindScene(DeviceScene deviceScene,String code,String password);
   /**设备操作记录*/
   HttpResult records(int userId);
   /**操作设备打开关闭并记录*/
   HttpResult control(int userId,int deviceId,String state);
   /**删除设备绑定*/
   HttpResult delete(int userId,int deviceId);
   /**修改设备信息*/
   HttpResult updata(DeviceScene deviceScene,String oldPassword,String newPassword);
   /**语音控制*/
   HttpResult voiceControl(int userId,String message);
}
