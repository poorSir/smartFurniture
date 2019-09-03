package com.zucc.smartfurniture.module.mine.viewCtrl;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zucc.smartfurniture.BR;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseRecycleViewVM;
import com.zucc.smartfurniture.common.BaseRevycleviewCtrl;
import com.zucc.smartfurniture.module.mine.model.CustomizationDeviceVM;
import com.zucc.smartfurniture.module.mine.model.rec.CustomizationDeviceRec;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.ListData;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.api.Customization;
import com.zucc.smartfurniture.views.SwipeListener;

import java.util.List;

import io.reactivex.Observable;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/6$ 9:30$
 * {@link com.zucc.smartfurniture.module.mine.ui.CustomizationDeviceAct}
 * <p/>
 */
public class CustomizationDeviceCtrl extends BaseRevycleviewCtrl{
    private String cusId;
    public CustomizationDeviceCtrl(String cusId){
        this.cusId = cusId;
        viewModel.set(new BaseRecycleViewVM<CustomizationDeviceVM>() {
            @Override
            public void setItemView(ItemBinding itemBinding, int position, CustomizationDeviceVM item) {
                itemBinding.set(BR.item, R.layout.mine_activity_customization_device_item);
            }
        });
        listener.set(new SwipeListener() {
            @Override
            public void refresh() {
                reqData();
            }

            @Override
            public void laodMore() {

            }

            @Override
            public void swipeInit(SwipeToLoadLayout swipeLayout) {
                setSwipeLayout(swipeLayout);
                getSwipeLayout().setLoadMoreEnabled(false);
            }
        });
    }

    public void reqData() {
        Observable<HttpResult<ListData<CustomizationDeviceRec>>> call = NetConnect.getService(Customization.class).getCusSceneList(cusId);
        call.compose(NetConnect.<HttpResult<ListData<CustomizationDeviceRec>>>setThread()).subscribe(new CallBackObserver<HttpResult<ListData<CustomizationDeviceRec>>>(getSwipeLayout()) {

            @Override
            public void onSuccess(HttpResult<ListData<CustomizationDeviceRec>> response) {
                if(response.getData() != null){
                    convert(response.getData().getList());
                }
            }
        });
    }

    private void convert(List<CustomizationDeviceRec> list) {
        viewModel.get().items.clear();
        if(list == null){
            return;
        }
        for(int i =0;i<list.size();i++){
            CustomizationDeviceVM vm = new CustomizationDeviceVM(this);
            vm.setCusId(cusId);
            vm.setUseScene(list.get(i).getUseScene());
            vm.setDeviceId(list.get(i).getDeviceId());
            vm.setDetail(list.get(i).getDetail());
            vm.setOperation(list.get(i).getOperation());
            viewModel.get().items.add(vm);
        }
    }
}
