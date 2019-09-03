package com.zucc.smartfurniture.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.zucc.smartfurniture.MyApplication;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/26$ 9:52$
 * Description: toast 工具类
 * <p/>
 */
public class ToastUtil {
    public static void show(String msg){
        show(MyApplication.getInstance().getActivity(),msg);
    }
    public static void show(Context context,String msg){
        Toast toast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        //显示在中间
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /***
     * 显示string 中的文字
     * @param id
     */
    public static void show(int id) {
        show(MyApplication.getInstance().getActivity(), MyApplication.getInstance().getActivity().getString(id));
    }
}
