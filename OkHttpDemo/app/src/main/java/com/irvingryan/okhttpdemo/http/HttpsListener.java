package com.irvingryan.okhttpdemo.http;


/**
 * Created by Administrator on 2016/2/29 0029.
 */
public interface HttpsListener {
    /**
     * 请求成功
     * @param what 标志是哪个请求
     * @param response 相应结果
     */
    void onSuccess(int what, String response);

    /**
     * 请求失败
     * @param what 标志是哪个请求
     * @param url url
     * @param tag tag,request为当前task维持的对象
     * @param exception 错误信息
     * @param responseCode 响应码
     * @param networkMillis 请求用时
     */
    void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis);

}
