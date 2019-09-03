package com.zucc.smartfurniture.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/26$ 10:04$
 * Description:文本工具类
 * <p/>
 */
public class TextUtil {
    /**文本是否为空*/
    public static boolean isEmpty(CharSequence str){
        if(str != null && str.length()>0){
            return false;
        }
        return true;
    }
    /**手机号验证规则*/
    public static boolean phoneCheck(String phone){
         /*
         * 电信
         * 中国电信手机号码开头数字
         * 2G/3G号段（CDMA2000网络）133、153、180、181、189
         * 4G号段 177
         *
         * 联通
         * 中国联通手机号码开头数字
         * 2G号段（GSM网络）130、131、132、155、156
         * 3G上网卡145
         * 3G号段（WCDMA网络）185、186
         * 4G号段 176、185[1]
         *
         * 移动
         * 中国移动手机号码开头数字
         * 2G号段（GSM网络）有134x（0-8）、135、136、137、138、139、150、151、152、158、159、182、183、184。
         * 3G号段（TD-SCDMA网络）有157、187、188
         * 3G上网卡 147
         * 4G号段 178
         * 补充
         *
         * 14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
         * 170号段为虚拟运营商专属号段，170号段的 11 位手机号前四位来区分基础运营商，其中 “1700” 为中国电信的转售号码标识，“1705” 为中国移动，“1709” 为中国联通。
         */
        if (TextUtil.isEmpty(phone)) {
            return false;
        }
        phone = phone.replaceAll("\\s*", "");
        Pattern regex   = Pattern.compile("^((1[358][0-9])|(14[57])|(17[03678]))\\d{8}$");
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }
    /**密码格式校验*/
    public static boolean passwordCheck(String password){
        if(isEmpty(password)){
            return false;
        }else if(password.length()>=6 && password.length()<=12){
            return true;
        }else{
            return false;
        }
    }
}
