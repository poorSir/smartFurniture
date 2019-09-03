package com.zucc.smartFurniture.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.regexp.internal.RE;
import com.zucc.smartFurniture.Socket.SendDevice;
import com.zucc.smartFurniture.Socket.SocketServer;
import com.zucc.smartFurniture.common.ReturnMsg;
import com.zucc.smartFurniture.mapper.CusSceneMapper;
import com.zucc.smartFurniture.mapper.DeviceMapper;
import com.zucc.smartFurniture.mapper.DeviceSceneMapper;
import com.zucc.smartFurniture.pojo.CusScene;
import com.zucc.smartFurniture.pojo.DeviceScene;
import com.zucc.smartFurniture.pojo.HttpResult;
import com.zucc.smartFurniture.pojo.sub.CusSceneSub;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.TextUI;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/15 16:27
 * Description:
 **/
@Service
public class CustomizationServiceImpl implements CustomizationService{
    @Autowired
    CusSceneMapper cusSceneMapper;
    @Autowired
    DeviceSceneMapper deviceSceneMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Override
    public HttpResult getCustomizationList(int userId, int cusId) {
        HttpResult result = new HttpResult();
        List<CusScene> list =  cusSceneMapper.queryByUAD(userId,cusId);
        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);
        if(list != null){
            List<CusSceneSub> cusSceneSubsList = new ArrayList<>();
            for(int i=0;i<list.size();i++){
                CusSceneSub cusSceneSub = new CusSceneSub();
                cusSceneSub.setDeviceId(list.get(i).getDevice_id()+"");
                cusSceneSub.setOperation(list.get(i).getOperation());
                DeviceScene deviceScene = deviceSceneMapper.queryDAU(userId,list.get(i).getDevice_id());
                cusSceneSub.setUseScene(deviceScene.getClassify());
                cusSceneSub.setDetail(deviceScene.getDetail());
                cusSceneSubsList.add(cusSceneSub);
            }
            Gson gson = new GsonBuilder().create();
            String data= gson.toJson(cusSceneSubsList);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("list",new JsonParser().parse(data).getAsJsonArray());
            result.setData(jsonObject);
        }
        return result;
    }


    @Override
    public HttpResult execute(int userId, int cusId) {
        HttpResult result = new HttpResult();
        List<CusScene> cusSceneList = cusSceneMapper.queryByUAD(userId,cusId);
        if(cusSceneList == null ){
            result.setCode("212");
            result.setMsg(ReturnMsg.Status_212);
        }else if(cusSceneList.size() == 0){
            result.setCode("212");
            result.setMsg(ReturnMsg.Status_212);
        } else{
            for(int i=0;i<cusSceneList.size();i++){
                Socket client = SocketServer.socketMap.get(cusSceneList.get(i).getDevice_id()+"");
                Boolean isClosed =SendDevice.isServerClose(client);
                if(isClosed){
                    result.setCode("215");
                    result.setMsg(ReturnMsg.Status_215);
                    return result;
                }
                boolean operateResult = SendDevice.sendArdunioDevice(cusSceneList.get(i).getDevice_id(),cusSceneList.get(i).getOperation());
                if(!operateResult){
                    DeviceScene deviceScene = deviceSceneMapper.queryDAU(userId,cusSceneList.get(i).getDevice_id());
                    result.setCode("214");
                    result.setMsg(deviceScene.getClassify()+deviceScene.getDetail()+ReturnMsg.Status_214);
                    return result;
                }
                int count = deviceMapper.updataDeviceState(cusSceneList.get(i).getDevice_id(),cusSceneList.get(i).getOperation());
                if(count <=0){
                    result.setCode("208");
                    result.setMsg(ReturnMsg.Status_208);
                    return result;
                }
            }
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
        }
        return result;
    }

    @Override
    public HttpResult addCusScene(int userId, int cusId, int deviceId, String operation) {
        HttpResult result = new HttpResult();
        CusScene cusScene = new CusScene();
        cusScene.setCus_id(cusId);
        cusScene.setUser_id(userId);
        cusScene.setDevice_id(deviceId);
        cusScene.setOperation(operation);
        CusScene cusSceneRec = cusSceneMapper.queryByUCD(cusId,userId,deviceId);
        if(cusSceneRec == null){
            int a = cusSceneMapper.addCusDevice(cusScene);
            if(a != 1){
                result.setCode("209");
                result.setMsg(ReturnMsg.Status_209);
                return result;
            }
        }
        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);
        return result;
    }

    @Override
    public HttpResult updataCusScene(int userId, int cusId, int deviceId, String operation) {
        HttpResult result = new HttpResult();
        int count = cusSceneMapper.updataCusScene(cusId,userId,deviceId,operation);
        if(count >0){
           result.setCode("200");
           result.setMsg(ReturnMsg.Status_200);
        }else{
            result.setCode("208");
            result.setMsg(ReturnMsg.Status_208);
        }
        return result;
    }

    @Override
    public HttpResult getDeviceList(int userId) {
        HttpResult result = new HttpResult();
        List<DeviceScene> deviceSceneList = deviceSceneMapper.queryByUserId(userId);
        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);
        if(deviceSceneList != null){
            Gson gson = new GsonBuilder().create();
            String data = gson.toJson(deviceSceneList);
            JsonObject  jsonObject = new JsonObject();
            jsonObject.add("list",new JsonParser().parse(data));
            result.setData(jsonObject);
        }
        return result;
    }
}
