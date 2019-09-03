package com.zucc.smartFurniture.mapper;

import com.zucc.smartFurniture.pojo.CusScene;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CusSceneMapper {
    /**通过uerId和cusId获取场景列表*/
    List<CusScene> queryByUAD(@Param("userId") int userId,@Param("cusId") int cusId);
    /**通过uerId和deviceId,cusId获取场景*/
    CusScene queryByUCD(@Param("cusId") int cusId,@Param("userId") int userId,@Param("deviceId") int deviceId);
    /**增加场景设备*/
    int addCusDevice(CusScene cusScene);
    /**更改定制场景操作*/
    int updataCusScene(@Param("cusId") int cusId,@Param("userId") int userId,@Param("deviceId") int deviceId,@Param("operation") String operation);
}
