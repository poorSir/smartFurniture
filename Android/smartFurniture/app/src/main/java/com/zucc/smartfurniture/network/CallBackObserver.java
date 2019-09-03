package com.zucc.smartfurniture.network;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.utils.TextUtil;
import com.zucc.smartfurniture.utils.ToastUtil;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/26$ 9:15$
 * Description:返回回调
 * <p/>
 */
public abstract  class CallBackObserver<T> implements Observer<T>{
    private Disposable d;
    private SwipeToLoadLayout swipeLayout;
    public CallBackObserver(SwipeToLoadLayout swipeLayout){
        this.swipeLayout = swipeLayout;
    }
    public CallBackObserver(){

    }
    @Override
    public void onSubscribe(Disposable d) {
        this.d=d;
    }

    @Override
    public void onNext(T tHttpResult) {
        if(swipeLayout != null && swipeLayout.isRefreshing()){
            swipeLayout.setRefreshing(false);
        }
        if(swipeLayout != null && swipeLayout.isLoadingMore()){
            swipeLayout.setLoadingMore(false);
        }
        HttpResult httpResult = (HttpResult) tHttpResult;
        if(httpResult.getCode().equals(Constant.String_200)){
            onSuccess(tHttpResult);
        }else{
            if(TextUtil.isEmpty(httpResult.getMsg())){
                ToastUtil.show(R.string.error_unknow);
            }else{
                ToastUtil.show(httpResult.getMsg());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        d.dispose();
        if(swipeLayout != null && swipeLayout.isRefreshing()){
            swipeLayout.setRefreshing(false);
        }
        if(swipeLayout != null && swipeLayout.isLoadingMore()){
            swipeLayout.setLoadingMore(false);
        }
        if(e instanceof HttpException){
            ToastUtil.show(((HttpException) e).code()+"");
        }
        if (e instanceof ApiException) {
            ToastUtil.show(((ApiException) e).getResult().toString());
        }
        if (e instanceof IOException) {
            ToastUtil.show(R.string.error_socket_timeout);
        }
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        d.dispose();
    }
    public abstract  void onSuccess(T response);
}
