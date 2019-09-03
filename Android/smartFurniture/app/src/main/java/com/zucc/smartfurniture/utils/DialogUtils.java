package com.zucc.smartfurniture.utils;

import android.content.Context;

import com.zucc.smartfurniture.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;
/**
 * Author: Chenming
 * E-mail: cm1@erongdu.com
 * Date: 2016/11/25 下午5:37
 * <p/>
 * Description: dialog 弹出框util
 */
public class DialogUtils {
    public static void showDialog(Context context,String contentText,
                                  OnSweetClickListener confirmClick, OnSweetClickListener cancelCLick) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context,  SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText(contentText)
                .setConfirmText(context.getString(R.string.comfirm))
                .setConfirmClickListener(confirmClick)
                .setCancelText(context.getString(R.string.cancel))
                .setCancelClickListener(cancelCLick);
        sweetAlertDialog.show();
    }
}
