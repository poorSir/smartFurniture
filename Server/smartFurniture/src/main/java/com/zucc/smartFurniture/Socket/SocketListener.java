package com.zucc.smartFurniture.Socket;

import com.zucc.smartFurniture.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-04-05 16:39
 * Description:
 **/
public class SocketListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获得Spring容器
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        //从Spring容器中获得SelectDataServlet的实例
        DeviceMapper deviceMapper = ctx.getBean(DeviceMapper.class);
        new Thread(()->{
            new SocketServer(deviceMapper);
        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
