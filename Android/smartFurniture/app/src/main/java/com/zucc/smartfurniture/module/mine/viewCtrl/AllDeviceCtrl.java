package com.zucc.smartfurniture.module.mine.viewCtrl;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zucc.smartfurniture.common.BaseRevycleviewCtrl;
import com.zucc.smartfurniture.module.control.model.rec.ControlItemRec;
import com.zucc.smartfurniture.module.mine.model.AllDeviceAdapter;
import com.zucc.smartfurniture.module.mine.model.AllDeviceOperatorVM;
import com.zucc.smartfurniture.module.mine.model.AllDeviceVM;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.ListData;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.api.OperateService;
import com.zucc.smartfurniture.views.SwipeListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/16$ 15:44$
 * {@link com.zucc.smartfurniture.module.mine.ui.AllDeviceAct}
 * <p/>
 */
public class AllDeviceCtrl extends BaseRevycleviewCtrl {
    List<MultiItemEntity> data =new ArrayList<>();
    public AllDeviceCtrl() {
        adapter.set(new AllDeviceAdapter(data));
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

    /** 请求数据 */
    public void reqData() {
        Observable<HttpResult<ListData<ControlItemRec>>> call = NetConnect.getService(OperateService.class).getControlList();
        call.compose(NetConnect.<HttpResult<ListData<ControlItemRec>>>setThread()).subscribe(new CallBackObserver<HttpResult<ListData<ControlItemRec>>>(getSwipeLayout()) {

            @Override
            public void onSuccess(HttpResult<ListData<ControlItemRec>> response) {
                convert(response.getData().getList());
            }
        });

    }

    private void convert(List<ControlItemRec> list) {
        if(list == null){
            return;
        }
        data.clear();
        for (int i = 0; i < list.size(); i++) {
            AllDeviceVM item = new AllDeviceVM();
            item.setId(list.get(i).getDevice_id());
            item.setClassify(list.get(i).getClassify());
            item.setDetail(list.get(i).getDetail());
            item.setIsOpen(false);
            item.addSubItem(new AllDeviceOperatorVM(item.getId().get(),AllDeviceCtrl.this));
            data.add(item);
        }
        adapter.get().notifyDataSetChanged();
    }
}
