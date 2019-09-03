package com.zucc.smartFurniture.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zucc.smartFurniture.common.UserAccess;
import com.zucc.smartFurniture.pojo.HttpResult;
import com.zucc.smartFurniture.service.CustomizationService;
import com.zucc.smartFurniture.utils.Response;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/15 16:23
 * Description:
 **/
@Controller
@RequestMapping("/operate/customization")
public class CustomizationController {

    @Autowired
    CustomizationService customizationService;
    /***
     * 获取定制场景列表
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/cusScene")
    public void getCusScene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusId = request.getParameter("cusId");
        HttpResult result = customizationService.getCustomizationList(Integer.valueOf(userId),Integer.valueOf(cusId));
        Response.responseStr(response, gson.toJson(result));
    }

    /***
     * 执行场景
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/execute")
    public void execute(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusId = request.getParameter("cusId");
        HttpResult result = customizationService.execute(Integer.valueOf(userId),Integer.valueOf(cusId));
        Response.responseStr(response,gson.toJson(result));
    }

    /***
     * 增加定制场景设备
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/cusSceneAdd")
    public void addCusScene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusId = request.getParameter("cusId");
        String deviceId = request.getParameter("deviceId");
        String operation = request.getParameter("operation");
        HttpResult result = customizationService.addCusScene(Integer.valueOf(userId),Integer.valueOf(cusId),Integer.valueOf(deviceId),operation);
        Response.responseStr(response,gson.toJson(result));
    }

    /***
     * 更改定制场景操作
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/cusSceneUpdata")
    public void updataCusScene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusId = request.getParameter("cusId");
        String deviceId = request.getParameter("deviceId");
        String operation = request.getParameter("operation");
        HttpResult result = customizationService.updataCusScene(Integer.valueOf(userId),Integer.valueOf(cusId),Integer.valueOf(deviceId),operation);
        Response.responseStr(response,gson.toJson(result));
    }

    /**
     * 获取所有设备
     * @param request
     * @param response
     */
    @UserAccess
    @RequestMapping("/getDeviceList")
    public void getDeviceList(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        HttpResult result = customizationService.getDeviceList(Integer.valueOf(userId));
        Response.responseStr(response,gson.toJson(result));
    }
}
