package com.zucc.smartfurniture.module.mine.viewCtrl;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.network.RouterUrl;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/5$ 16:19$
 * {@link com.zucc.smartfurniture.module.mine.ui.CustomizationAct}
 * <p/>
 */
public class CustomizationCtrl {
    /**场景编号*/
    private String cusId;
    public CustomizationCtrl(){

    }
    /**回家*/
    public void gomeHomeClick(){
        cusId = Constant.String_1;
        open(cusId);
    }
    /**离家*/
    public void leaveHomeClick(){
        cusId = Constant.String_2;
        open(cusId);
    }
    /**会客*/
    public void huikeClick(){
        cusId = Constant.String_3;
        open(cusId);
    }
    /**晚安*/
    public void sleepClick(){
        cusId = Constant.String_4;
        open(cusId);
    }
    public void open(String cusId){
        ARouter.getInstance().build(RouterUrl.customizationDevice).withString(BaseParam.cusId,cusId).navigation();
    }
}
