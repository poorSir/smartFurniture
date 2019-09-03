package com.zucc.smartfurniture;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mob.MobSDK;
import com.zucc.smartfurniture.common.AppConfig;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/21$ 16:35$
 * <p/>
 */
public class MyApplication extends Application {
    /** 字体图标 */
    private        Typeface           iconTypeFace;
    private static MyApplication      instance;
    private static Activity           sActivity;
    public  static  Context            context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        iconfontInit();
        initARouter();
        initMobSDk();
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                sActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    /** ARrouter初始化 */
    private void initARouter() {
        if (AppConfig.isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    /** mobsdk初始化 */
    private void initMobSDk() {
        // 通过代码注册你的AppKey和AppSecret
        MobSDK.init(this, AppConfig.mob_sms_appkey, AppConfig.mob_sms_appSecret);
    }

    /** iconfontInit初始化 */
    private void iconfontInit() {
        instance = this;
        iconTypeFace = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
    }

    public Typeface getIconTypeFace() {
        return iconTypeFace;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public Activity getActivity() {
        return sActivity;
    }
}
