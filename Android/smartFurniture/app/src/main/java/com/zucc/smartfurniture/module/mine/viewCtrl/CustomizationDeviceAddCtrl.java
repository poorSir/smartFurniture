package com.zucc.smartfurniture.module.mine.viewCtrl;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zucc.smartfurniture.BR;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseRecycleViewVM;
import com.zucc.smartfurniture.common.BaseRevycleviewCtrl;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.databinding.MineActivityCustomizationAddBinding;
import com.zucc.smartfurniture.module.control.model.rec.ControlItemRec;
import com.zucc.smartfurniture.module.mine.model.CustomizationDeviceAddVM;
import com.zucc.smartfurniture.module.mine.model.DeviceSelectListener;
import com.zucc.smartfurniture.module.mine.ui.CustomizationDeviceAddAct;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.ListData;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.api.Customization;
import com.zucc.smartfurniture.views.SwipeListener;
import com.zucc.smartfurniture.views.dialog.CustomizationDeviceAddDialog;

import java.util.List;

import io.reactivex.Observable;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/6$ 11:46$
 * {@link com.zucc.smartfurniture.module.mine.ui.CustomizationDeviceAddAct}
 * <p/>
 */
public class CustomizationDeviceAddCtrl extends BaseRevycleviewCtrl{
    private DeviceSelectListener deviceSelectListener;
    private String cusId;
    private String deviceId;
    private MineActivityCustomizationAddBinding binding;
    private CustomizationDeviceAddAct act;
    public CustomizationDeviceAddCtrl(final MineActivityCustomizationAddBinding binding, String cusId, CustomizationDeviceAddAct act){
        this.cusId  = cusId;
       this.act = act;
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
        viewModel.set(new BaseRecycleViewVM<CustomizationDeviceAddVM>() {
            @Override
            public void setItemView(ItemBinding itemBinding, int position, CustomizationDeviceAddVM item) {
                itemBinding.set(BR.item, R.layout.mine_activity_customization_add_item).bindExtra(BR.listener,getOnEvnetListener());
            }
        });
        deviceSelectListener = new DeviceSelectListener() {
            @Override
            public void open() {
                reqAddDevive(Constant.String_1);
            }

            @Override
            public void close() {
                reqAddDevive(Constant.String_0);
            }
        };
        viewModel.get().setOnEvnetListener(new BaseRecycleViewVM.OnEvnetListener<CustomizationDeviceAddVM>() {
            @Override
            public void onClickListener(CustomizationDeviceAddVM item) {
                deviceId = item.getDeviceId();
                new CustomizationDeviceAddDialog(binding.getRoot().getContext(),deviceSelectListener).show();
            }
        });
    }

    /***
     * 获取设备列表
     */
    private void reqData() {
        Observable<HttpResult<ListData<ControlItemRec>>> call = NetConnect.getService(Customization.class).getDeviceList();
        call.compose(NetConnect.<HttpResult<ListData<ControlItemRec>>>setThread()).subscribe(new CallBackObserver<HttpResult<ListData<ControlItemRec>>>(getSwipeLayout()) {
            @Override
            public void onSuccess(HttpResult<ListData<ControlItemRec>> response) {
                if(response.getData() != null){
                    convert(response.getData().getList());
                }
            }
        });

    }

    private void convert(List<ControlItemRec> list) {
        viewModel.get().items.clear();
        if(list == null){
            return;
        }
        for(int i = 0;i<list.size();i++){
            CustomizationDeviceAddVM vm = new CustomizationDeviceAddVM();
            vm.setDeviceId(list.get(i).getDevice_id());
            vm.setClassify(list.get(i).getClassify());
            vm.setDetail(list.get(i).getDetail());
            viewModel.get().items.add(vm);
        }
    }

    /***
     * 增加定制场景设备
     * 1-打开  0-关闭
     */
    private void reqAddDevive(String operation){
       Observable<HttpResult> call = NetConnect.getService(Customization.class).addCusScene(cusId,deviceId,operation);
        call.compose(NetConnect.<HttpResult>setThread()).subscribe(new CallBackObserver<HttpResult>() {
            @Override
            public void onSuccess(HttpResult response) {
                act.finish();
            }
        });
    }
}
