package com.zucc.smartFurniture.utils;

import com.zucc.smartFurniture.pojo.DeviceScene;
import com.zucc.smartFurniture.pojo.VoiceOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-04-05 13:50
 * Description:分析语音具体执行效果
 **/
public class VoiceRecogintionUtil {
    public static VoiceOperation recogintion(String message, List<DeviceScene> list){
        if(list == null || list.size() == 0){
            return null;
        }
        VoiceOperation voiceOperation =new VoiceOperation();
        int openIndex = message.indexOf("打开");
        int closeIndex = message.indexOf("关闭");
        if(openIndex >=0){
            message = message.substring(openIndex+2);
        }else if(closeIndex>=0){
            message = message.substring(closeIndex+2);
        }else{
            return null;
        }
        for(int i=0;i<list.size();i++){
            int detailIndex =message.indexOf(list.get(i).getClassify()+list.get(i).getDetail());
            if(detailIndex>=0){
                if(openIndex >=0){
                    voiceOperation.setOperate(1);
                }else{
                    voiceOperation.setOperate(0);
                }
                voiceOperation.setDeviceId(list.get(i).getDevice_id());
                return voiceOperation;
            }
        }
        return null;
    }
}
