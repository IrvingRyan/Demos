package com.irvingryan.okhttpdemo.http;

import okhttp3.Request;

/**
 * Created by wentao on 2016/7/15.
 */
public class APIManager {
    private static APIManager instance;
    private APIManager() {
    }

    public static APIManager getInstance() {
        if (instance==null){
            synchronized (APIManager.class){
                if (instance==null)
                    instance=new APIManager();
            }
        }
        return instance;
    }
    public void getPhoneLocale(HttpsListener httpsListener,String phone){
        Request request =new Request
                .Builder()
                .get()
                .url("http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone="+phone)
                .header("apikey","d76d18c11ec89bb47fdf511b5349b198")
                .build();
        HttpsRequest.getInstance().get(0,request,httpsListener);
    }
}
