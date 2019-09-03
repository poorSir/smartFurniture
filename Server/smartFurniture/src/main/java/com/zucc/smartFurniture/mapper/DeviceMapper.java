package com.zucc.smartFurniture.mapper;

import com.zucc.smartFurniture.pojo.Device;
import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {
    /**添加设备*/
    int add(Device device);
    /**通过code与password查找*/
    Device queryByCAP(@Param("code") String code, @Param("password") String password);
    /**通过code查找*/
    Device queryByCode(@Param("code") String code);
    /**更改设备状态*/
    int updataDeviceState(@Param("id") int id,@Param("state") String state);
    /**通过id*/
    Device queryById(int id);
    /**修改设备密码*/
    int updataDevicePassword(@Param("id") int id,@Param("newPassword")String newPassword,@Param("oldPassword") String oldPassword);
}
