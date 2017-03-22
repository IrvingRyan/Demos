package com.github.ir.camerademo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.ir.camerademo.view.TextureViewImp;

public class TextureViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TextureViewImp(this);
    }
}
