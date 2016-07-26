package com.irvingryan.okhttpdemo.http;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wentao on 2016/7/15.
 */
public class HttpsRequest {
    private static final String TAG="HttpsRequest";
    private static HttpsRequest instance;
    private OkHttpClient client;
    private Executor executor= Executors.newFixedThreadPool(3);
    private static final int CONNECT_TIME_OUT = 20;
    private static final int READ_TIME_OUT = 20;
    private static final int WRITE_TIME_OUT = 20;

    private HttpsRequest(){
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
    public OkHttpClient getOkHttpClient() {
        SSLContext sslContext = SSLContextUtils.getSSLContext();
        SSLSocketFactory sslSocketFactory = null;
        if (sslContext != null) {
            sslSocketFactory = sslContext.getSocketFactory();
        }
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) SSLContextUtils.trustManagers)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }
    /**
     * 同步请求 自己开启线程的方式
     * @param what
     * @param request
     * @param httpsListener
     */
    public void get(final int what, final Request request, final HttpsListener httpsListener){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"request start");
                try {
                    final Response response=getOkHttpClient().newCall(request).execute();
                    if (response.isSuccessful()){
                        sendSuccessResponse(response.body().string(), httpsListener, what);
                    }else {
                        sendFailedResponse(new Exception(),httpsListener,what);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendFailedResponse(final Exception e, final HttpsListener httpsListener, final int what) {
        MainThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "request failed response message== " + e.getMessage() + " response body ==");
                httpsListener.onFailed(what,null,null,e,400,0);
            }
        });
    }

    private void sendSuccessResponse(final String response, final HttpsListener httpsListener, final int what) {
        MainThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "request success response message== " + response + " response body ==");
                httpsListener.onSuccess(what, response);
            }
        });
    }
    public void post(final int what, final Request request, final HttpsListener httpsListener){
        Log.i(TAG,"main thread id is "+Thread.currentThread().getId());
        getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG,"callback thread id is "+Thread.currentThread().getId());
                sendFailedResponse(e,httpsListener,what);
//                Log.i(TAG, "request failed response message== " + call.request().body() + " response body ==");
//                httpsListener.onFailed(what,call.request().url().toString(),call.request().tag(),e,400,0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.i(TAG, "request success response message== " + response.message() + " response body ==");
                try {
                    Log.i(TAG,"callback thread id is "+Thread.currentThread().getId());
                    Log.i(TAG,"response header is "+response.headers().toString());
                    sendSuccessResponse(response.body().string(), httpsListener, what);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                httpsListener.onSuccess(what, response);
            }
        });
    }
}
