package com.zucc.smartfurniture;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.zucc.smartfurniture.baiduvoice.DigitalDialogInput;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.databinding.ActivityMainBinding;
import com.zucc.smartfurniture.module.control.ui.ControlFrag;
import com.zucc.smartfurniture.module.home.ui.HomeFrag;
import com.zucc.smartfurniture.module.mine.ui.MineFrag;
import com.zucc.smartfurniture.module.record.ui.RecordFrag;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.RouterUrl;
import com.zucc.smartfurniture.network.api.OperateService;
import com.zucc.smartfurniture.utils.TextUtil;
import com.zucc.smartfurniture.utils.ToastUtil;
import com.zucc.smartfurniture.views.iconfont.IconFontModule;
import com.zucc.smartfurniture.views.iconfont.IconFonts;

import java.util.ArrayList;

import io.reactivex.Observable;

@Route(path = RouterUrl.Main_Act)
public class MainActivity extends BaseActivity {
    /**首页按钮*/
    private     IconDrawable homeNormal;
    /**首页按钮-选择*/
    private     IconDrawable homeSelect;
    /**遥控按钮*/
    private     IconDrawable controlNormal;
    /**遥控按钮-选择*/
    private     IconDrawable controlSelect;
    /**记录按钮*/
    private     IconDrawable recordNormal;
    /**记录按钮-选择*/
    private     IconDrawable recordSelect;
    /**我的按钮*/
    private     IconDrawable mineNormal;
    /**我的按钮-选择*/
    private     IconDrawable mineSelect;
    private     String       TAG_HOME = "tag_home";
    private     String       TAG_CONTROL = "tag_control";
    private     String       TAG_RECORD = "tag_record";
    private     String       TAG_MINE = "tag_mine";
    private               FragmentTransaction transaction;
    private               HomeFrag            homeFrag;
    private               ControlFrag         controlFrag;
    private               RecordFrag          recordFrag;
    private               MineFrag            mineFrag;
    private               ActivityMainBinding binding;
    public static DigitalDialogInput  input;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        // 设置导航栏模式 + 背景（需要在添加tab前面，不然不会有效果）
        binding.tabs
                .setMode(BottomNavigationBar.MODE_FIXED)   //未选中的Item会显示文字，没有换挡动画
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);//点击的时候有水波纹效果
        binding.tabs
                .setActiveColor(R.color.select)
                .setInActiveColor(R.color.normal)
                .setBarBackgroundColor(R.color.select);
        iconfontInit();
        binding.tabs
                .addItem(new BottomNavigationItem(homeSelect,getResources().getString(R.string.home))
                        .setInactiveIcon(homeNormal).setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(controlSelect,getResources().getString(R.string.control))
                        .setInactiveIcon(controlNormal).setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(recordSelect,getResources().getString(R.string.record))
                        .setInactiveIcon(recordNormal).setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(mineSelect,getResources().getString(R.string.mine))
                        .setInactiveIcon(mineNormal).setActiveColorResource(R.color.white))
                .setTabSelectedListener(listener)
                .setFirstSelectedPosition(0)
                .initialise();
        binding.tabs.selectTab(0);
    }
    /**iconfont初始化*/
    private void iconfontInit() {
        Iconify.with(new IconFontModule());
        homeNormal    =new IconDrawable(this, IconFonts.icon_home).color(getResources().getColor(R.color.normal)).sizeDp(48);
        homeSelect    =new IconDrawable(this,IconFonts.icon_home).color(getResources().getColor(R.color.select)).sizeDp(48);
        controlNormal =new IconDrawable(this,IconFonts.icon_control).color(getResources().getColor(R.color.normal)).sizeDp(48);
        controlSelect =new IconDrawable(this,IconFonts.icon_control).color(getResources().getColor(R.color.select)).sizeDp(48);
        recordNormal  =new IconDrawable(this,IconFonts.icon_record).color(getResources().getColor(R.color.normal)).sizeDp(48);
        recordSelect  =new IconDrawable(this,IconFonts.icon_record).color(getResources().getColor(R.color.select)).sizeDp(48);
        mineNormal    =new IconDrawable(this,IconFonts.icon_mine).color(getResources().getColor(R.color.normal)).sizeDp(48);
        mineSelect    =new IconDrawable(this,IconFonts.icon_mine).color(getResources().getColor(R.color.select)).sizeDp(48);
    }

    /**底部导航监听*/
    public BottomNavigationBar.OnTabSelectedListener listener =new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            bottomNavigationSelect(position);
        }

        @Override
        public void onTabUnselected(int position) {
            bottomNavigationUnSelect(position);
        }

        @Override
        public void onTabReselected(int position) {
            bottomNavigationSelect(position);
        }
    };
    /**底部导航点击*/
    public void  bottomNavigationSelect(int position){
        FragmentManager     manager =  MainActivity.this.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (position){
            case Constant.Number_0:
                if(null == homeFrag){
                    homeFrag = (HomeFrag) manager.findFragmentByTag(TAG_HOME);
                }
                if(null == homeFrag){
                    homeFrag = HomeFrag.newInstance();

                    transaction.add(R.id.content,homeFrag,TAG_HOME);
                }else{
                    transaction.show(homeFrag);
                }
                break;
            case Constant.Number_1:
                if(null == controlFrag){
                    controlFrag = (ControlFrag) manager.findFragmentByTag(TAG_CONTROL);
                }
                if(null == controlFrag){
                    controlFrag = ControlFrag.newInstance();

                    transaction.add(R.id.content,controlFrag,TAG_CONTROL);
                }else{
                    transaction.show(controlFrag);
                }
                break;
            case Constant.Number_2:
                if(null == recordFrag){
                    recordFrag = (RecordFrag) manager.findFragmentByTag(TAG_RECORD);
                }
                if(null == recordFrag){
                    recordFrag = RecordFrag.newInstance();

                    transaction.add(R.id.content,recordFrag,TAG_RECORD);
                }else{
                    transaction.show(recordFrag);
                }
                break;
            case Constant.Number_3:
                if(null == mineFrag){
                    mineFrag = (MineFrag) manager.findFragmentByTag(TAG_MINE);
                }
                if(null == mineFrag){
                    mineFrag = MineFrag.newInstance();

                    transaction.add(R.id.content,mineFrag,TAG_MINE);
                }else{
                    transaction.show(mineFrag);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }
    /**底部导航取消点击*/
    public void bottomNavigationUnSelect(int position){
        FragmentManager     manager =  MainActivity.this.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch(position){
            case Constant.Number_0:
                if(null == homeFrag){
                    homeFrag = (HomeFrag) manager.findFragmentByTag(TAG_HOME);
                }
                if(null != homeFrag){
                    transaction.hide(homeFrag);
                }
                break;
            case Constant.Number_1:
                if(null == controlFrag){
                    controlFrag = (ControlFrag) manager.findFragmentByTag(TAG_CONTROL);
                }
                if(null != controlFrag){
                    transaction.hide(controlFrag);
                }
                break;
            case Constant.Number_2:
                if(null == recordFrag){
                    recordFrag = (RecordFrag) manager.findFragmentByTag(TAG_RECORD);
                }
                if(null != recordFrag){
                    transaction.hide(recordFrag);
                }
                break;
            case Constant.Number_3:
                if(null == mineFrag){
                    mineFrag = (MineFrag) manager.findFragmentByTag(TAG_MINE);
                }
                if(null != mineFrag){
                    transaction.hide(mineFrag);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }
    /**切换tab*/
    public void setTab(int position){
        bottomNavigationUnSelect(Constant.Number_0);
        bottomNavigationUnSelect(Constant.Number_1);
        bottomNavigationUnSelect(Constant.Number_2);
        bottomNavigationUnSelect(Constant.Number_3);
        binding.tabs.selectTab(position);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == BaseParam.control_scene_request && resultCode == RESULT_OK){
            if(controlFrag != null){
                controlFrag.onActivityResult(requestCode,resultCode,data);
            }
        }
        if(requestCode == BaseParam.baidu_voice_request){
            String message = "";
            if (resultCode == RESULT_OK) {
                ArrayList results = data.getStringArrayListExtra("results");
                if (results != null && results.size() > 0) {
                    message += results.get(0);
                }
            }
            if(TextUtil.isEmpty(message)){
                ToastUtil.show(R.string.recognition_error);
            }else{
                Observable<HttpResult> call = NetConnect.getService(OperateService.class).voiceControl(message);
                call.compose(NetConnect.<HttpResult>setThread()).subscribe(new CallBackObserver<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult response) {
                        ToastUtil.show(R.string.success);
                    }
                });
            }
        }

    }
    public void setInput(DigitalDialogInput dialogInput){
        input = dialogInput;
    }
}
