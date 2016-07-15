package com.irvingryan.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.irvingryan.okhttpdemo.http.APIManager;
import com.irvingryan.okhttpdemo.http.HttpsListener;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG="MainActivity";

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get:
                getNumId();
                break;
        }
    }

    private void getNumId() {
        APIManager.getInstance().getPhoneLocale(httpsListener,"15210011578");
    }
    HttpsListener httpsListener=new HttpsListener() {
        @Override
        public void onSuccess(int what, Response response) {
            try {
                //这个方法只能获取一次，再次获取为空
                String responseString=response.body().string();
                Log.i(TAG,"httpsListener onSuccess and response == "+responseString);
                responseText.setText(responseString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

        }
    };
}
