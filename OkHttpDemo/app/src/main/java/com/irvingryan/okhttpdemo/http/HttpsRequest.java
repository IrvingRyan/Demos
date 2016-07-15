package com.irvingryan.okhttpdemo.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wentao on 2016/7/15.
 */
public class HttpsRequest {
    private static final String TAG="HttpsRequest";
    private static HttpsRequest instance;
    private final OkHttpClient client;
    private Executor executor= Executors.newFixedThreadPool(3);
    private static final int REQUEST_SUCCESS=0;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REQUEST_SUCCESS:
                    httpsListener.onSuccess(msg.arg1, (Response) msg.obj);
                    break;
            }

        }
    };
    private HttpsListener httpsListener;

    private HttpsRequest(){
        client=new OkHttpClient();
    }

    public static HttpsRequest getInstance() {
        if (instance==null){
            synchronized (HttpsRequest.class){
                if (instance==null)
                    instance=new HttpsRequest();
            }
        }
        return instance;
    }
    public void get(final int what, final Request request, final HttpsListener httpsListener){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"request start");
                try {
                    Response response=client.newCall(request).execute();
                    if (response.isSuccessful()){
//                        String responseString = response.body().b();
                        Log.i(TAG,"request success response message== "+response.message()+" response body ==");
                        HttpsRequest.this.httpsListener=httpsListener;
                        Message message=new Message();
                        message.what=REQUEST_SUCCESS;
                        message.arg1=what;
                        message.obj=response;
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
