package com.zucc.smartfurniture.module.control.viewCtrl;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zucc.smartfurniture.BR;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseRecycleViewVM;
import com.zucc.smartfurniture.common.BaseRevycleviewCtrl;
import com.zucc.smartfurniture.databinding.ControlFragmentBinding;
import com.zucc.smartfurniture.module.control.model.ControlItemVM;
import com.zucc.smartfurniture.module.control.model.rec.ControlItemRec;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.ListData;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.api.OperateService;
import com.zucc.smartfurniture.views.SwipeListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/1$ 14:08$
 * {@link com.zucc.smartfurniture.module.control.ui.ControlFrag}
 * <p/>
 */
public class ControlCtrl extends BaseRevycleviewCtrl{
    public ControlCtrl(final ControlFragmentBinding binding){
        viewModel.set(new BaseRecycleViewVM<ControlItemVM>() {
            @Override
            public void setItemView(ItemBinding itemBinding, int position, ControlItemVM item) {
                itemBinding.set(BR.item, R.layout.control_item).bindExtra(BR.listener,getOnEvnetListener());
            }
        });
        listener.set(new SwipeListener() {
            @Override
            public void refresh() {
                reqData();
            }

            @Override
            public void laodMore() {
                reqData();
            }

            @Override
            public void swipeInit(SwipeToLoadLayout swipeLayout) {
                setSwipeLayout(swipeLayout);
                getSwipeLayout().setLoadMoreEnabled(false);
            }
        });
        viewModel.get().setOnEvnetListener(new BaseRecycleViewVM.OnEvnetListener<ControlItemVM>() {
            @Override
            public void onClickListener(ControlItemVM item) {
                Observable<HttpResult> call = NetConnect.getService(OperateService.class).controlState(item.getDeviceId(),item.getState());
                call.compose(NetConnect.<HttpResult>setThread()).subscribe(new CallBackObserver<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult response) {
                    }
                });
            }
        });

    }

    public void reqData() {
        Observable<HttpResult<ListData<ControlItemRec>>> call = NetConnect.getService(OperateService.class).getControlList();
        call.compose(NetConnect.<HttpResult<ListData<ControlItemRec>>>setThread()).subscribe(new CallBackObserver<HttpResult<ListData<ControlItemRec>>>(getSwipeLayout()) {

            @Override
            public void onSuccess(HttpResult<ListData<ControlItemRec>> response) {
                convert(response.getData().getList());
            }
        });
    }

    private void convert(List<ControlItemRec> data) {
        if(data == null){
            return;
        }
        viewModel.get().items.clear();
        List<ControlItemVM> list =new ArrayList<>();
        for(int i=0;i<data.size();i++){
            ControlItemVM vm =new ControlItemVM();
            vm.setDeviceId(data.get(i).getDevice_id());
            vm.setClassify(data.get(i).getClassify());
            vm.setDetail(data.get(i).getDetail());
            vm.setState(data.get(i).getState());
            list.add(vm);
        }
        viewModel.get().items.addAll(list);
    }
}
