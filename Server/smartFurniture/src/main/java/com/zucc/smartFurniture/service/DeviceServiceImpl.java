package com.zucc.smartFurniture.service;

import com.google.gson.*;
import com.zucc.smartFurniture.Socket.SendDevice;
import com.zucc.smartFurniture.Socket.SocketServer;
import com.zucc.smartFurniture.common.ReturnMsg;
import com.zucc.smartFurniture.common.UserAccess;
import com.zucc.smartFurniture.mapper.DeviceMapper;
import com.zucc.smartFurniture.mapper.DeviceSceneMapper;
import com.zucc.smartFurniture.mapper.RecordsMapper;
import com.zucc.smartFurniture.pojo.*;
import com.zucc.smartFurniture.pojo.sub.RecordSub;
import com.zucc.smartFurniture.pojo.sub.SceneSub;
import com.zucc.smartFurniture.utils.StringUtil;
import com.zucc.smartFurniture.utils.TimeUtil;
import com.zucc.smartFurniture.utils.VoiceRecogintionUtil;
import jdk.nashorn.internal.parser.JSONParser;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/7 11:45
 * Description:
 **/
@Service
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    DeviceSceneMapper deviceSceneMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    RecordsMapper recordsMapper;
    /***
     * 获取设备场景
     * @param userId
     * @return
     */
    @Override
    public HttpResult getScene(int userId) {
        HttpResult result=new HttpResult();
        List<DeviceScene> list= deviceSceneMapper.queryByUserId(userId);
        List<SceneSub> listSub = new ArrayList<>();
        if(list == null){
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        for(int i=0;i<list.size();i++){
           Device device = deviceMapper.queryById(list.get(i).getDevice_id());
            SceneSub sceneSub = new SceneSub();
            sceneSub.setDevice_id(list.get(i).getDevice_id());
            sceneSub.setDetail(list.get(i).getDetail());
            sceneSub.setState(device.getState());
            sceneSub.setClassify(list.get(i).getClassify());
            listSub.add(sceneSub);
        }
        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);
        Gson gson = new GsonBuilder().create();
        String data =  gson.toJson(listSub);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("list",new JsonParser().parse(data).getAsJsonArray());
        result.setData(jsonObject);
        return result;
    }

    /***
     * 绑定设备场景
     * @param deviceScene
     * @param code
     * @param password
     * @return
     */
    @Override
    public HttpResult bindScene(DeviceScene deviceScene, String code, String password) {
        HttpResult result=new HttpResult();
        Device device = deviceMapper.queryByCAP(code,password);
        if(device == null){
            result.setCode("207");
            result.setMsg(ReturnMsg.Status_207);
        }else{
            deviceScene.setDevice_id(device.getId());
            DeviceScene deviceScene1 = deviceSceneMapper.queryDAU(deviceScene.getUser_id(),device.getId());
            if(deviceScene1 == null){
                int addResult = deviceSceneMapper.add(deviceScene);
                if(addResult != 1){
                    result.setCode("206");
                    result.setMsg(ReturnMsg.Status_206);
                }else{
                    result.setCode("200");
                    result.setMsg(ReturnMsg.Status_200);
                }
            }else{
                result.setCode("211");
                result.setMsg(ReturnMsg.Status_211);
            }

        }
        return result;
    }

    /***
     * 获取操作记录
     * @param userId
     * @return
     */
    @Override
    public HttpResult records(int userId) {
        HttpResult result=new HttpResult();
        Gson gson = new GsonBuilder().create();
        List<RecordSub> recordSubList = new ArrayList<>();
        List<Records> records = recordsMapper.queryByUserId(userId);
        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);
        if(records != null){
            for(int i=0;i<records.size();i++){
                RecordSub recordSub = new RecordSub();
                recordSub.setDeviceId(records.get(i).getDevice_id()+"");
                recordSub.setType(records.get(i).getType());
                recordSub.setState(records.get(i).getState());
                recordSub.setTime(records.get(i).getTime());
                DeviceScene deviceScene = deviceSceneMapper.queryDAU(userId,records.get(i).getDevice_id());
                if(deviceScene == null){
                    return result;
                }
                recordSub.setUseScene(deviceScene.getClassify());
                recordSub.setDetail(deviceScene.getDetail());
                recordSubList.add(recordSub);
            }
            String data = gson.toJson(recordSubList);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("list",new JsonParser().parse(data).getAsJsonArray());
            result.setData(jsonObject);
        }
        return result;
    }

    @Override
    public HttpResult control(int userId, int deviceId, String state) {
        HttpResult result=new HttpResult();
        Socket client = SocketServer.socketMap.get(deviceId+"");
        Boolean isClosed =SendDevice.isServerClose(client);
        if(isClosed){
            result.setCode("215");
            result.setMsg(ReturnMsg.Status_215);
            return result;
        }
        boolean operateResult = SendDevice.sendArdunioDevice(deviceId,state);
        if(!operateResult){
            result.setCode("214");
            result.setMsg(ReturnMsg.Status_214);
           return result;
        }
        int count = deviceMapper.updataDeviceState(deviceId,state);
        if(count > 0){
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            Records records = new Records();
            records.setUser_id(userId);
            records.setDevice_id(deviceId);
            records.setState(state);
            records.setType("1");
            records.setTime(TimeUtil.getTimeData());
            int isSuccess = recordsMapper.add(records);
            if(isSuccess != 1){
                result.setCode("209");
                result.setMsg(ReturnMsg.Status_209);
            }else{
                result.setCode("200");
                result.setMsg(ReturnMsg.Status_200);
            }
        }else{
            result.setCode("208");
            result.setMsg(ReturnMsg.Status_208);
        }
        return result;
    }


    @Override
    public HttpResult delete(int userId, int deviceId) {
        HttpResult result=new HttpResult();
        int count = deviceSceneMapper.delete(userId,deviceId);
        if(count>0){
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
        }else{
            result.setCode("210");
            result.setMsg(ReturnMsg.Status_210);
        }
        return result;
    }

    @Override
    public HttpResult updata(DeviceScene deviceScene, String oldPassword, String newPassword) {
        HttpResult result=new HttpResult();
        int count = deviceMapper.updataDevicePassword(deviceScene.getDevice_id(),newPassword,oldPassword);
        if(count >0){
            int resultCount=deviceSceneMapper.updataDeviceSceneName(deviceScene.getUser_id(),deviceScene.getDevice_id(),deviceScene.getClassify(),deviceScene.getDetail());
            if(resultCount>0){
                result.setCode("200");
                result.setMsg(ReturnMsg.Status_200);
                return result;
            }
            //回退
            deviceMapper.updataDevicePassword(deviceScene.getDevice_id(),oldPassword,newPassword);
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult voiceControl(int userId, String message) {
        HttpResult result=new HttpResult();
        List<DeviceScene> list = deviceSceneMapper.queryByUserId(userId);
        VoiceOperation voiceOperation =VoiceRecogintionUtil .recogintion(message,list);
        if(voiceOperation ==null){
            result.setCode("213");
            result.setMsg(ReturnMsg.Status_213);
        }else{
            result=control(userId,voiceOperation.getDeviceId(),voiceOperation.getOperate()+"");
        }
        return result;
    }
}
