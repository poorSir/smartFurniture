package com.zucc.smartfurniture.module.home.viewCtrl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zucc.smartfurniture.MainActivity;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.SimpleTransApplication;
import com.zucc.smartfurniture.baiduvoice.BaiduASRDigitalDialog;
import com.zucc.smartfurniture.baiduvoice.ChainRecogListener;
import com.zucc.smartfurniture.baiduvoice.DigitalDialogInput;
import com.zucc.smartfurniture.baiduvoice.MessageStatusRecogListener;
import com.zucc.smartfurniture.baiduvoice.MyRecognizer;
import com.zucc.smartfurniture.baiduvoice.OnlineRecogParams;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.databinding.HomeFragmentBinding;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.api.Customization;
import com.zucc.smartfurniture.utils.ConvertUtil;
import com.zucc.smartfurniture.utils.ToastUtil;
import com.zucc.smartfurniture.views.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/31$ 14:13$
 * {@link com.zucc.smartfurniture.module.home.ui.HomeFrag}
 * <p/>
 */
public class HomeCtrl {
    private HomeFragmentBinding binding;
    private Banner              banner;
    private Activity            activity;
    private MainActivity        mainActivity;
    private MyRecognizer myRecognizer;
    private OnlineRecogParams apiParams;
    private ChainRecogListener listener;
    public HomeCtrl(HomeFragmentBinding binding,Activity activity){
        this.binding = binding;
        this.activity = activity;
        mainActivity = (MainActivity) activity;
        banner = binding.banner;
        initBanner();
        initRecog();
    }
    /**设置banner*/
    private void initBanner() {
        List images =new ArrayList();
        images.add(ConvertUtil.idToUri(activity,R.drawable.banner1));
        images.add(ConvertUtil.idToUri(activity,R.drawable.banner2));
        images.add(ConvertUtil.idToUri(activity,R.drawable.banner3));
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**远程控制*/
    public void control(){

        mainActivity.setTab(Constant.Number_1);
    }
    /**操作记录*/
    public void record(){
        mainActivity.setTab(Constant.Number_2);
    }

    public void execute(int cusId){
        Observable<HttpResult> call = NetConnect.getService(Customization.class).execute(cusId+"");
        call.compose(NetConnect.<HttpResult>setThread()).subscribe(new CallBackObserver<HttpResult>() {
            @Override
            public void onSuccess(HttpResult response) {
                ToastUtil.show(R.string.success);
            }
        });
    }
    public void execute1(){
        execute(1);
    }
    public void execute2(){
        execute(2);
    }
    public void execute3(){
        execute(3);
    }
    public void execute4(){
        execute(4);
    }

    protected void initRecog() {
        listener = new ChainRecogListener();
        // DigitalDialogInput 输入 ，MessageStatusRecogListener可替换为用户自己业务逻辑的listener
        listener.addListener(new MessageStatusRecogListener());
        myRecognizer = new MyRecognizer(activity, listener); // DigitalDialogInput 输入
        apiParams = new OnlineRecogParams(activity);
    }
    /**开始语音*/
    public  void start(View view) {
        SharedPreferences   sp     = PreferenceManager.getDefaultSharedPreferences(activity);
        Map<String, Object> params = apiParams.fetch(sp);  // params可以手动填入

        // BaiduASRDigitalDialog的输入参数
        DigitalDialogInput input = new DigitalDialogInput(myRecognizer, listener, params);
        ((MainActivity)activity).setInput(input);
        Intent intent = new Intent(activity, BaiduASRDigitalDialog.class);
        // 在BaiduASRDialog中读取
        ((SimpleTransApplication) activity.getApplicationContext()).setDigitalDialogInput(input);

        // 修改对话框样式
        // intent.putExtra(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_ORANGE_DEEPBG);

        //        running = true;
        activity.startActivityForResult(intent, BaseParam.baidu_voice_request);
    }
}

