package com.zucc.smartFurniture.common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zucc.smartFurniture.pojo.HttpResult;
import com.zucc.smartFurniture.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/8 17:27
 * Description:
 **/
@Component
@Aspect
public class PermissionAspect {
    @Autowired
    UserService userService;
    private static final Logger logger = LogManager.getLogger(PermissionAspect.class);
    @Before("@annotation(com.zucc.smartFurniture.common.UserAccess)")
    public void checkPermission(JoinPoint joinPoint) throws Exception{
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        for(int i=0;i<args.length;i++){
            if(args[i] instanceof HttpServletRequest){
                request = (HttpServletRequest)args[i];
            }
            if(args[i] instanceof HttpServletResponse){
                response = (HttpServletResponse)args[i];
            }
        }
        Gson gson = new GsonBuilder().create();
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        Log(request);
        PrintWriter out;
        String token = request.getParameter("token");
        if(request.getParameter("userId") == null){
            throw new Exception("no userid");
        }
        int id = Integer.valueOf(request.getParameter("userId")).intValue();
        HttpResult result = userService.checkToken(id, token);
        if(!result.getCode().equals("200")) {
            out = response.getWriter();
            out.write(gson.toJson(result));
            out.flush();
            throw new Exception(result.getMsg());
        }
    }
    public void Log(HttpServletRequest request){
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry entry : set) {
            logger.info(entry.getKey() + ":" + entry.getValue());
        }
    }
}
