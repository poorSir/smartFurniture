package com.zucc.smartFurniture.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-04-05 19:55
 * Description:
 **/
public class StringUtil {
    /***
     *去除空格，水平制表符，换行，回车
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
