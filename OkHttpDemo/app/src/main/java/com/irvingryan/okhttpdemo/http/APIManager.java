package com.irvingryan.okhttpdemo.http;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

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
    public void getPhoneLocale(int what,HttpsListener httpsListener,String phone){
        Request request =new Request
                .Builder()
                .get()
                .url(HttpConfig.PHONE_URL+phone)
                .header("apikey","d76d18c11ec89bb47fdf511b5349b198")
                .build();
        HttpsRequest.getInstance().get(what,request,httpsListener);
    }
    public void requestTestStore(int what,HttpsListener httpsListener){
        RequestBody requestBody=new FormBody.Builder()
                .add("key","665685e5b3d0ffbf74b839b21df1d864")
                .add("subject","1")
                .add("model","c1")
                .build();
        Request request=new Request.Builder()
                .url(HttpConfig.DRIVER_TEST_URL)
                .post(requestBody)
                .build();
        HttpsRequest.getInstance().post(what,request,httpsListener);
    }
}
