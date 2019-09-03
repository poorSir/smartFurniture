package com.zucc.smartfurniture.utils;

import android.app.Activity;
import android.content.Context;

import com.zucc.smartfurniture.MyApplication;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/15$ 9:28$
 * <p/>
 */
public class ContextUtil {
    /**返回当前activity*/
    public static Activity getActivity() {
        return MyApplication.getInstance().getActivity();
    }
    /**返回当前页面的context*/
    public static Context getContext(){
        return getActivity().getBaseContext();
    }
    /**返回string资源*/
    public static String getString(int id){
        return getContext().getResources().getString(id);
    }
}
