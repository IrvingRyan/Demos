package com.irvingryan.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.irvingryan.okhttpdemo.http.APIManager;
import com.irvingryan.okhttpdemo.http.HttpsListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MainActivity";
    @Bind(R.id.post)
    Button post;

    @Bind(R.id.get)
    Button get;
    @Bind(R.id.response_text)
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        get.setOnClickListener(this);
        post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get:
                getNumId();
                break;
            case R.id.post:
                getTestSubject();
                break;
        }
    }

    private void getTestSubject() {
        APIManager.getInstance().requestTestStore(1, httpsListener);
    }

    private void getNumId() {
        APIManager.getInstance().getPhoneLocale(0, httpsListener, "15210011578");
    }

    HttpsListener httpsListener = new HttpsListener() {
        @Override
        public void onSuccess(int what, String response) {
            if (what == 0) {
                try {
                    Log.i(TAG, "httpsListener onSuccess and response == " + response);
                    responseText.setText(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (what == 1) {
                try {
                    Log.i(TAG, "httpsListener onSuccess and response == " + response);
                    responseText.setText(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            if (what == 0) {
                Log.i(TAG, "httpsListener onSuccess and response == " + exception);
            } else if (what == 1) {
                Log.i(TAG, "httpsListener onSuccess and response == " + exception);
            }
        }
    };
}
