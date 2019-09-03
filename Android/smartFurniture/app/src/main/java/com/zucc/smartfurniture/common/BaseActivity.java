package com.zucc.smartfurniture.common;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zucc.smartfurniture.R;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/23$ 9:18$
 * <p/>
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager       tintManager = new SystemBarTintManager(this);
            // 激活状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            // 设置一个状态栏颜色
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
        //初始化@Autowired注解的字段
        ARouter.getInstance().inject(this);
    }
}
