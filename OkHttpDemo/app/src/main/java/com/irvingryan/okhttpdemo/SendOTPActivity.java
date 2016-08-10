package com.irvingryan.okhttpdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.irvingryan.okhttpdemo.framework.base.BaseActivity;
import com.irvingryan.okhttpdemo.http.APIManager;
import com.irvingryan.okhttpdemo.http.HttpsListener;
import com.irvingryan.okhttpdemo.http.HttpsRequest;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by globalroam on 2015/8/6.
 */
public class SendOTPActivity extends BaseActivity implements View.OnClickListener {
    //    private ActiveDialog activeDialog;
    private static final int WHAT_REQUEST_OTP = 0x004;
    @Bind(R.id.active_service)
    Button activeService;
    @Bind(R.id.cancel_action)
    Button cancelAction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_otp_2);
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        activeService.setOnClickListener(this);
        cancelAction.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.gc();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.active_service:
                APIManager.getInstance().requestToken(WHAT_REQUEST_OTP, null, httpsListener, false);
                break;
            case R.id.cancel_action:
                HttpsRequest.getInstance().cancel(WHAT_REQUEST_OTP);
                break;
        }
    }

    HttpsListener httpsListener = new HttpsListener() {
        @Override
        public void onSuccess(int what, String response) {
            if (what == WHAT_REQUEST_OTP) {
                try {
                    Log.i(TAG, "requestOtp onSuccess response : " + response);
                    Gson gson = new Gson();
                    ReceiveOTP otp = gson.fromJson(response, ReceiveOTP.class);
                    if (otp != null && "1000".equals(otp.getCode())) {
//                        go2VerifyOTPActivity(otp.getVerCode());
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            if (what == WHAT_REQUEST_OTP) {
                Log.i(TAG, "requestOtp onFailed response : exception = " + exception.toString() + ";responseCode = " + responseCode + ";url = " + url);

                showToast(getString(R.string.request_otp_failure_tips));
            }
        }

        @Override
        public void onCanceled(int what) {

        }

    };


}
