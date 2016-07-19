package com.irvingryan.okhttpdemo;

/**
 *  Created by globalroam on 2015/9/18.
 */
public class HttpsConfig {
    public static final String PLATFORM="ANDROID";
    public  static final String ABOUT_PFINGO_URL = "http://tiny.cc/aboutpfingo";
    public static final String FAQ_URL = "http://tiny.cc/pfingofaq";
    public static final String CONTACT_US_URL = "http://tiny.cc/pfingocontact";

    public static String TOKEN_API_URL= "https://dev.pfingo.com:8243/token";
    public static final String HTTP_BASE_URL = "https://dev.pfingo.com:8243/pfingo";
    public static final String REQUEST_LOGIN_URL = HTTP_BASE_URL + "/v1/login/";
    public static final String REQUEST_ACCOUNT = HTTP_BASE_URL + "/v1/accounts/";
    public static final String REQUEST_USER_INFO = HTTP_BASE_URL + "/v1/pkg/accounts";

    public static final String requestCallLogURL(String account_id) {
        return REQUEST_ACCOUNT + account_id + "/usages/cdrs";
    }
    public static final String requestAccount(String phoneNumber) {
        return REQUEST_ACCOUNT + phoneNumber + "/callLog";
    }
    public static final String QUEST_OTP = HTTP_BASE_URL + "/v1/phoneOtpsending";
    public static final String VERIFY_OTP = HTTP_BASE_URL + "/v1/phoneOtpverification";

    public static final String REQUEST_CALL_BACK = HTTP_BASE_URL + "/v1/callback";
    public static final String RECORDER_CALL_BACK = HTTP_BASE_URL+"/v1/unavailableRecord";

    public static final String REQUEST_VERSION = HTTP_BASE_URL + "/v1/versions";
//-------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_USER_PACKAGES=HTTP_BASE_URL+"/v1/packages";

    public static final String TOP_UP_BASE_URL="http://dev.pfingo.com:8083/PfingoBilling/spring/paypal";
//    public static final String TOP_UP_BASE_URL=MyApplication.getInstance().isStaging(MyApplication.getInstance().getVersionName())?"http://172.27.1.201:8083/PfingoBilling/spring/paypal":"http://api.pfingo.com:8083/PfingoBilling/spring/paypal";
    public static final String USER_BALANCE=TOP_UP_BASE_URL+"/getUserBalance";
    public static final String USER_PACKAGE=TOP_UP_BASE_URL+"/getUserPackage";
    public static final String PAYMENT_LOG=TOP_UP_BASE_URL+"/getPaymentLog";
    public static final String TOP_UP_EXPRESS_CHECKOUT_URL=TOP_UP_BASE_URL+"/setExpressCheckout";
    public static final String TOP_UP_AUTOPAY_URL=TOP_UP_BASE_URL+"/initBillingAgreement";
    public static final String TOP_UP_SUCCESS_URL=TOP_UP_BASE_URL+"/success";
    public static final String TOP_UP_AUTOPAY_SUCCESS_URL=TOP_UP_BASE_URL+"/autoPay";
    public static final String TOP_UP_FAILURE_URL=TOP_UP_BASE_URL+"/fail";
    public static final String TOP_UP_CANCEL_URL="/PfingoBilling/spring/paypal/fail";

    public static final String CALLING_CARD_TOP_UP_URL=HTTP_BASE_URL+"/v1/callingcard";

    public static final String REQUEST_BALANCE_TRANSFER =HTTP_BASE_URL + "/v1/transferBalance";
}
