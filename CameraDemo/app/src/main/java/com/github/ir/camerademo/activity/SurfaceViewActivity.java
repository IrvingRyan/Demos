package com.github.ir.camerademo.activity;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.github.ir.camerademo.view.CameraPreview;

public class SurfaceViewActivity extends AppCompatActivity implements Camera.PreviewCallback{
    private String TAG="SurfaceViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CameraPreview cameraPreview = new CameraPreview(this, Camera.open());
        cameraPreview.addPreviewCallback(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(cameraPreview,layoutParams);
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.i(TAG,"onPreviewFrame");
    }
}
