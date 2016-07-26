package com.irvingryan.okhttpdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.irvingryan.okhttpdemo.framework.base.BaseActivity;
import com.irvingryan.okhttpdemo.http.APIManager;
import com.irvingryan.okhttpdemo.http.HttpsListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by globalroam on 2015/8/6.
 */
public class SendOTPActivity extends BaseActivity implements View.OnClickListener {
    //    private ActiveDialog activeDialog;
    private static final int WHAT_REQUEST_OTP = 0x004;
    @Bind(R.id.country_code)
    TextView countryCode;
    @Bind(R.id.edit_number)
    EditText editNumber;
    @Bind(R.id.active_service)
    Button activeService;
    private boolean hasSim;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_otp_2);
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        activeService.setOnClickListener(this);
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
                SendOTP sendOTP = new SendOTP();
                sendOTP.setUserId(countryCode.getText().toString() + editNumber.getText().toString());
                sendOTP.setMethod("SMS");
                APIManager.getInstance().requestToken(WHAT_REQUEST_OTP, sendOTP, httpsListener, false);
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

    };


}
