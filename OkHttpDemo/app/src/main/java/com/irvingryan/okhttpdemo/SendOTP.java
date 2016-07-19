package com.irvingryan.okhttpdemo;

/**
 * Created by Administrator on 2016/3/7 0007.
 */
public class SendOTP {
    private String userId;
    private String otp;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private String method;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
