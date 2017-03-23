package com.github.ir.camerademo.activity;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.github.ir.camerademo.R;
import com.github.ir.camerademo.model.VideoProcessor;
import com.github.ir.camerademo.util.SizeUtil;

import java.io.IOException;

public class FFmpegActivity extends BaseActivity implements SurfaceHolder.Callback,
        Camera.PreviewCallback,View.OnClickListener {
private String TAG="FFmpegActivity";
    private SurfaceTexture mSurfaceTexture;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmpeg);
        SurfaceView surfaceView =$(R.id.surface_view);
        $(R.id.start).setOnClickListener(this);
        $(R.id.stop).setOnClickListener(this);
        surfaceView.getHolder().addCallback(this);
        mSurfaceTexture = new SurfaceTexture(10);
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.i(TAG,"onPreviewFrame"+ data);
        if (VideoProcessor.recording){
            VideoProcessor.getInstance(this).recording(data);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera = Camera.open();
            mCamera.setPreviewTexture(mSurfaceTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();
        Camera.Size optimalPreviewSize = SizeUtil.getOptimalPreviewSize(parameters.getSupportedPreviewSizes(), width, height);
        parameters.setPreviewSize(optimalPreviewSize.width,optimalPreviewSize.height);
        mCamera.setParameters(parameters);
        mCamera.setPreviewCallback(this);
        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera!=null){
            mCamera.stopPreview();
            mCamera.release();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                VideoProcessor.getInstance(this).startRecorder();
                break;
            case R.id.stop:
                VideoProcessor.getInstance(this).startRecorder();
                break;
        }
    }


}
