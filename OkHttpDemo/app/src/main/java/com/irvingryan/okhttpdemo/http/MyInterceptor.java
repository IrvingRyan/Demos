package com.irvingryan.okhttpdemo.http;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wentao on 2016/8/25.
 */
public class MyInterceptor implements Interceptor {
    private String TAG="MyInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);
        Log.i(TAG,"request == "+request.toString());
        Log.i(TAG,"response == "+response.toString());
        return response;
    }
}
