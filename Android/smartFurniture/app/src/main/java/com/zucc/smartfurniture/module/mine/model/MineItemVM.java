package com.zucc.smartfurniture.module.mine.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.zucc.smartfurniture.BR;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/15$ 9:11$
 * <p/>
 */
public class MineItemVM extends BaseObservable{
    /**左边iconfont*/
    private String leftIcon;
    /**右边iconfont*/
    private String rightIcon;
    /**标题*/
    private int title;
    public MineItemVM(String leftIcon,int title,String rightIcon){
        setLeftIcon(leftIcon);
        setRightIcon(rightIcon);
        setTitle(title);
    }
    @Bindable
    public String getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(String leftIcon) {
        this.leftIcon = leftIcon;
        notifyPropertyChanged(BR.leftIcon);
    }
    @Bindable
    public String getRightIcon() {
        return rightIcon;
    }

    public void setRightIcon(String rightIcon) {
        this.rightIcon = rightIcon;
        notifyPropertyChanged(BR.rightIcon);
    }
    @Bindable
    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
}
