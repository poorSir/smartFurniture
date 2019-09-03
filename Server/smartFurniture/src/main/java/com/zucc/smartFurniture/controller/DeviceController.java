package com.zucc.smartFurniture.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zucc.smartFurniture.common.UserAccess;
import com.zucc.smartFurniture.interceptor.TokenInterceptor;
import com.zucc.smartFurniture.pojo.DeviceScene;
import com.zucc.smartFurniture.pojo.HttpResult;
import com.zucc.smartFurniture.service.DeviceService;
import com.zucc.smartFurniture.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/7 11:09
 * Description:
 **/
@Controller
@RequestMapping("/operate/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    /***
     * 获取设备场景
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/scene")
    public void scene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        HttpResult result = deviceService.getScene(Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }

    /**
     * 绑定设备场景
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/bind")
    public void bind(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DeviceScene deviceScene = new DeviceScene();
        String userId = request.getParameter("userId");
        String deviceCode = request.getParameter("deviceCode");
        String devicePassword = request.getParameter("devicePassword");
        String classify = request.getParameter("classify");
        String detail = request.getParameter("detail");
        deviceScene.setUser_id(Integer.valueOf(userId));
        deviceScene.setClassify(classify);
        deviceScene.setDetail(detail);
        HttpResult result = deviceService.bindScene(deviceScene,deviceCode,devicePassword);
        Response.responseStr(response, gson.toJson(result));
    }

    /**
     * 操作记录
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/record")
    public void record(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        HttpResult result = deviceService.records(Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }

    /***
     * 操作设备
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/control")
    public void control(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");
        String state = request.getParameter("state");
        HttpResult result =deviceService.control(Integer.valueOf(userId),Integer.valueOf(deviceId),state);
        Response.responseStr(response, gson.toJson(result));
    }

    /***
     * 删除设备绑定
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");
        HttpResult result = deviceService.delete(Integer.valueOf(userId),Integer.valueOf(deviceId));
        Response.responseStr(response, gson.toJson(result));
    }

    /**
     * 修改设备信息
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/updata")
    public void updata(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DeviceScene deviceScene = new DeviceScene();
        deviceScene.setDevice_id(Integer.valueOf(request.getParameter("deviceId")));
        deviceScene.setUser_id(Integer.valueOf(request.getParameter("userId")));
        deviceScene.setClassify(request.getParameter("useScene"));
        deviceScene.setDetail(request.getParameter("detail"));
        String oldDevicePassword = request.getParameter("oldDevicePassword");
        String newDevicePassword = request.getParameter("newDevicePassword");
        HttpResult result = deviceService.updata(deviceScene,oldDevicePassword,newDevicePassword);
        Response.responseStr(response, gson.toJson(result));
    }
    /***
     * 语音控制
     */
    @UserAccess
    @RequestMapping("/voiceControl")
    public void voice(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String message = request.getParameter("message");
        HttpResult result = deviceService.voiceControl(Integer.valueOf(userId),message);
        Response.responseStr(response, gson.toJson(result));
    }
}
