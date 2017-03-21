package com.github.ir.camerademo.activity;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.github.ir.camerademo.view.CameraPreview;
import com.github.ir.camerademo.view.SurfaceDisplay;

public class SurfaceTextureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //有crash没有解决
        SurfaceDisplay surfaceDisplay = new SurfaceDisplay(this,800,800);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(surfaceDisplay,layoutParams);
    }
}
