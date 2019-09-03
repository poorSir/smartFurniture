package com.zucc.smartfurniture.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zucc.smartfurniture.common.AppConfig;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/25$ 14:04$
 * <p/>
 */
public class NetConnect {
    // 网络请求超时时间值(s)
    private static final int    DEFAULT_TIMEOUT = 30;
    private static NetConnect instance;
    private Retrofit retrofit;
    public static NetConnect getInstance(){
        if(instance == null){
            instance = new NetConnect();
        }
        return instance;
    }
    public NetConnect(){
        // 创建一个OkHttpClient
        OkHttpClient.Builder builder =new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);
        // 打印参数
        builder.addInterceptor(new BaseParamsInterceptor());
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        // 失败后尝试重新请求
        builder.retryOnConnectionFailure(true);
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.baseUrl+"smartFurniture/")
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //根据接口的字节码文件对象获取接口对象
    public static <T> T getService(Class<T> clazz){
       T service = NetConnect.getInstance().retrofit.create(clazz);
        return service;
    }
    /**链式调度转换*/
    public static <T>ObservableTransformer<T,T> setThread(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
