package com.zucc.smartfurniture.module.record.viewCtrl;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zucc.smartfurniture.BR;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseRecycleViewVM;
import com.zucc.smartfurniture.common.BaseRevycleviewCtrl;
import com.zucc.smartfurniture.module.record.model.RecordItemVM;
import com.zucc.smartfurniture.module.record.model.rec.RecordRec;
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
 * Date: 2017/11/1$ 14:24$
 * {@link com.zucc.smartfurniture.module.record.ui.RecordFrag}
 * <p/>
 */
public class RecordCtrl extends BaseRevycleviewCtrl{
    public RecordCtrl(){
        viewModel.set(new BaseRecycleViewVM() {
            @Override
            public void setItemView(ItemBinding itemBinding, int position, Object item) {
                itemBinding.set(BR.items, R.layout.record_item);
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
    public void reqData(){
        Observable<HttpResult<ListData<RecordRec>>> call = NetConnect.getService(OperateService.class).getRecordList();
        call.compose(NetConnect.<HttpResult<ListData<RecordRec>>>setThread()).subscribe(new CallBackObserver<HttpResult<ListData<RecordRec>>>(getSwipeLayout()) {
            @Override
            public void onSuccess(HttpResult<ListData<RecordRec>>response) {
                if(response.getData()!= null){
                   convert(response.getData().getList());
                }
            }
        });

    }

    private void convert(List<RecordRec> data) {
        if(data == null){
            return;
        }
        viewModel.get().items.clear();
        List<RecordItemVM> list=new ArrayList();
        for(int i=0;i<data.size();i++){
            RecordItemVM vm=new RecordItemVM();
            vm.setState(data.get(i).getState());
            vm.setType(data.get(i).getType());
            vm.setDetail(data.get(i).getDetail());
            vm.setTime(data.get(i).getTime());
            vm.setUseScene(data.get(i).getUseScene());
            list.add(vm);
        }
        viewModel.get().items.addAll(list);
    }
}
