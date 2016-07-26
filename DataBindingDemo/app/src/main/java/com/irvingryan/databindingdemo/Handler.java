package com.irvingryan.databindingdemo;

import android.view.View;
import android.widget.Toast;

/**
 * Created by wentao on 2016/7/26.
 */
public class Handler {
    public void onClickFriend(View view) {
        Toast.makeText(view.getContext(),"点了",Toast.LENGTH_SHORT).show();
    }
}
