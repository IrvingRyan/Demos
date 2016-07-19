package com.irvingryan.okhttpdemo;

/**
 * Created by Administrator on 2016/3/7 0007.
 */
public class ReceiveOTP {
    private String user_id;
    private String code;
    private String verCode;
    private String desc;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
