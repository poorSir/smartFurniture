package com.zucc.smartfurniture.module.mine.model;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.common.MultipleQuickAdapter;
import com.zucc.smartfurniture.databinding.AllDeviceBinding;
import com.zucc.smartfurniture.databinding.AllDeviceOperatorBinding;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/30$ 9:53$
 * <p/>
 */
public class AllDeviceAdapter extends MultipleQuickAdapter<MultiItemEntity,AllDeviceAdapter.AllDeviceViewHolder> {
    public AllDeviceAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(Constant.Number_0,R.layout.all_device);
        addItemType(Constant.Number_1,R.layout.all_device_operator);
    }

    @Override
    protected void convert(final AllDeviceAdapter.AllDeviceViewHolder viewHolder,MultiItemEntity multiItemEntity) {
        switch(viewHolder.getItemViewType()){
            case Constant.Number_0:
                AllDeviceBinding binding = (AllDeviceBinding) viewHolder.getConvertView().getTag(R.id.all_device_id);
                final AllDeviceVM allDeviceVM = (AllDeviceVM) multiItemEntity;
                binding.setItem(allDeviceVM);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       int position = viewHolder.getAdapterPosition();
                        if(allDeviceVM.isExpanded()) {
                            collapse(position);
                            allDeviceVM.setIsOpen(false);
                        }else{
                            allDeviceVM.setIsOpen(true);
                            expand(position);
                        }

                    }
                });
                break;
            case Constant.Number_1:
                AllDeviceOperatorBinding operatorBinding = (AllDeviceOperatorBinding) viewHolder.getConvertView().getTag(R.id.all_device_id);
                AllDeviceOperatorVM allDeviceOperatorVM = (AllDeviceOperatorVM) multiItemEntity;
                operatorBinding.setItem(allDeviceOperatorVM);
                break;
            default:
                break;
        }
    }

    @Override
    protected AllDeviceViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == Constant.Number_0){
            AllDeviceBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.all_device,parent,false);
            view=binding.getRoot();
            view.setTag(R.id.all_device_id,binding);
        }else if (viewType == Constant.Number_1){
            AllDeviceOperatorBinding operatorBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.all_device_operator,parent,false);
            view=operatorBinding.getRoot();
            view.setTag(R.id.all_device_id,operatorBinding);
        }
        return new AllDeviceViewHolder(view);
    }

    public class AllDeviceViewHolder extends BaseViewHolder {
        public AllDeviceViewHolder(View view) {
            super(view);
        }
    }
}
