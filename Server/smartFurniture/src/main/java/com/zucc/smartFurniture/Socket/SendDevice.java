package com.zucc.smartFurniture.Socket;

import com.zucc.smartFurniture.utils.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-04-06 13:11
 * Description:
 **/
public class SendDevice {
    /**发送设备请求*/
    public static  boolean sendArdunioDevice(int deviceId,String state) {
        Socket client = SocketServer.socketMap.get(deviceId+"");
        String send;
        if("0".equals(state)){
            send ="{off}";
        }else {
            send ="{on}";
        }
        try {
            OutputStream out = client.getOutputStream();
            InputStream in = client.getInputStream();
            out.write(send.getBytes());
            out.flush();
            System.out.println("send finish");
            long time=System.currentTimeMillis();
            while(System.currentTimeMillis()-time<2000){
                while(in.available()>0) {
                    byte[] data = new byte[in.available()];
                    in.read(data);
                    String resultData = new String(data);
                    resultData = StringUtil.replaceBlank(resultData);
                    System.out.println("resultData=" + resultData);
                    if(resultData.contains("error")){
                        return false;
                    }else{
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 判断是否断开连接，断开返回true,没有返回false
     * @param socket
     * @return
     */
    public static Boolean isServerClose(Socket socket){
        try{
            socket.sendUrgentData(0xFF);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        }catch(Exception se){
            return true;
        }
    }
}
