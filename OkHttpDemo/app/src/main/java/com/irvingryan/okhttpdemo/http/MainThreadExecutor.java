package com.irvingryan.okhttpdemo.http;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * 将所有的网络请求结果交给UI线程处理
 * Created by wentao on 2016/7/15.
 */
public class MainThreadExecutor implements Executor {
    private static MainThreadExecutor instance;
    private MainThreadExecutor() {
    }

    public static MainThreadExecutor getInstance() {
        if (instance==null){
            synchronized (APIManager.class){
                if (instance==null)
                    instance=new MainThreadExecutor();
            }
        }
        return instance;
    }
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable r)
    {
        handler.post(r);
    }
}
