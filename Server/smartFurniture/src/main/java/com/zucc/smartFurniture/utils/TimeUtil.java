package com.zucc.smartFurniture.utils;

import com.google.gson.annotations.Until;

import java.util.Calendar;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/12 15:37
 * Description:
 **/
public class TimeUtil {
    public static String getTimeData(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String result =year + "/" + (month+1) + "/" + date + " " +hour + ":" +minute + ":" + second;
        return result;
    }
}
