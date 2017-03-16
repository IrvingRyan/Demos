package com.github.ir.camerademo;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Camera.PreviewCallback,SurfaceHolder.Callback{

    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setFixedSize(200, 200); // 预览大小設置
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(this);
        initCamera();
    }

    public void initCamera(){
        try {
            camera = Camera.open(0);
            camera.setPreviewDisplay(surfaceHolder);
            //设置摄像头的参数
            Camera.Parameters parameters = camera.getParameters();
            parameters.setPreviewSize(200,200);
            parameters.setPictureSize(200,200);
            parameters.setPreviewFormat(ImageFormat.NV21);
            camera.setDisplayOrientation(90);
            parameters.setRotation(90);

            //获取摄像头致辞的数据格式
            List<Integer> supportedPreviewFormats = parameters.getSupportedPreviewFormats();
            Log.i(""," supportedPreviewFormats  = "+supportedPreviewFormats.toString());
            camera.setParameters(parameters);
            camera.setPreviewCallback(this);
            camera.startPreview();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.i("onPreviewFrame","data "+Arrays.toString(data));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
