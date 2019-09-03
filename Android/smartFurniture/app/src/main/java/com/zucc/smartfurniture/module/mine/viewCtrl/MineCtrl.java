package com.zucc.smartfurniture.module.mine.viewCtrl;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zucc.smartfurniture.BR;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.common.BaseRecycleViewVM;
import com.zucc.smartfurniture.common.BaseRevycleviewCtrl;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.module.mine.model.MineItemVM;
import com.zucc.smartfurniture.network.RouterUrl;
import com.zucc.smartfurniture.utils.ContextUtil;
import com.zucc.smartfurniture.utils.SharePreferenceInfo;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/1$ 14:31$
 * {@link com.zucc.smartfurniture.module.mine.ui.MineFrag}
 * <p/>
 */
public class MineCtrl extends BaseRevycleviewCtrl{
    public MineCtrl(){
        viewModel.set(new BaseRecycleViewVM<MineItemVM>() {
            @Override
            public void setItemView(ItemBinding itemBinding, int position, MineItemVM item) {
                itemBinding.set(BR.item, R.layout.mine_item).bindExtra(BR.listener,getOnEvnetListener());
            }
        });
        viewModel.get().items.add(Constant.Number_0,new MineItemVM(ContextUtil.getString(R.string.iconfont_password),R.string.change_passoword,ContextUtil.getString(R.string.iconfont_forword)));
        viewModel.get().items.add(Constant.Number_1,new MineItemVM(ContextUtil.getString(R.string.iconfont_device),R.string.mine_all_device,ContextUtil.getString(R.string.iconfont_forword)));
        viewModel.get().items.add(Constant.Number_2,new MineItemVM(ContextUtil.getString(R.string.iconfont_scene),R.string.mine_scene_setting,ContextUtil.getString(R.string.iconfont_forword)));
        viewModel.get().setOnEvnetListener(new BaseRecycleViewVM.OnEvnetListener<MineItemVM>() {
            @Override
            public void onClickListener(MineItemVM item) {
                switch(item.getTitle()){
                    case R.string.change_passoword:
                        ARouter.getInstance().build(RouterUrl.ChangePassword).withString(BaseParam.type,Constant.String_0).navigation();
                        break;
                    case R.string.mine_all_device:
                        ARouter.getInstance().build(RouterUrl.allDevice).navigation();
                        break;
                    case R.string.mine_scene_setting:
                        ARouter.getInstance().build(RouterUrl.customization).navigation();
                        break;
                }
            }
        });
    }
    /**退出登陆*/
    public void exit(View view){
        SharePreferenceInfo.getInstance().remove(BaseParam.userId);
        SharePreferenceInfo.getInstance().remove(BaseParam.token);
        SharePreferenceInfo.getInstance().remove(BaseParam.isLogin);
        System.exit(0);
    }
}
