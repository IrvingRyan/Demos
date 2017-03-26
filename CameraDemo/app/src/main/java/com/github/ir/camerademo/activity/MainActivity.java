package com.github.ir.camerademo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;

import com.github.ir.camerademo.util.BitmapUtills;
import com.github.ir.camerademo.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Camera.PreviewCallback, TextureView.SurfaceTextureListener {

    private String TAG = "MainActivity";

    private Camera camera;
    private int height = 240;
    private int width = 320;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        $(R.id.surface_view).setOnClickListener(this);
        $(R.id.surface_texture).setOnClickListener(this);
        $(R.id.texture_view).setOnClickListener(this);
        $(R.id.ffmpeg).setOnClickListener(this);
        $(R.id.open_camera).setOnClickListener(this);

    }

    public <T extends View> T $(@IdRes int id) {
        return (T) findViewById(id);
    }

    public void initCamera(SurfaceHolder holder) {
        try {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            int numberOfCameras = Camera.getNumberOfCameras();
            Log.i("numberOfCameras", "numberOfCameras = " + numberOfCameras);
            for (int i = 0; i < numberOfCameras; i++) {
                Camera.getCameraInfo(i, cameraInfo);
                Log.i("cameraInfo", " facing = " + cameraInfo.facing + " orientation =" + cameraInfo.orientation + " canDisableShutterSound =" + cameraInfo.canDisableShutterSound);
            }

            camera = Camera.open();
//            camera.setPreviewDisplay(holder);
            //设置摄像头的参数
            Camera.Parameters parameters = camera.getParameters();
            parameters.setPreviewSize(width, height);
            parameters.setPictureSize(width, height);
            parameters.setPreviewFormat(ImageFormat.NV21);
            camera.setDisplayOrientation(90);
            parameters.setRotation(90);

            //获取摄像头支持的数据格式
            List<Integer> supportedPreviewFormats = parameters.getSupportedPreviewFormats();
            Log.i("", " supportedPreviewFormats  = " + supportedPreviewFormats.toString());

            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            for (Camera.Size size : supportedPreviewSizes) {
                Log.i("supportedPreviewSizes", " height:" + size.height + " width:" + size.width);
            }

            List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
            for (Camera.Size size : supportedPictureSizes) {
                Log.i("supportedPictureSizes", " height:" + size.height + " width:" + size.width);

            }

            camera.setParameters(parameters);
            camera.setPreviewCallback(this);
            camera.startPreview();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void destoryCamera() {
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.i(TAG, "onPreviewFrame");
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size previewSize = parameters.getPreviewSize();
        //以下操作将byte数据转换为bitmap 并显示
        YuvImage image = new YuvImage(data, ImageFormat.NV21, previewSize.width, previewSize.height, null);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compressToJpeg(new Rect(0, 0, previewSize.width, previewSize.height), 100, stream);

        Bitmap bitmap = BitmapFactory.decodeByteArray(stream.toByteArray(), 0, stream.size());

        Bitmap timeStamp = BitmapUtills.addTimeStamp(bitmap);

    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.surface_texture:
                startActivity(new Intent(this,SurfaceTextureActivity.class));
                break;
            case R.id.texture_view:
                startActivity(new Intent(this,TextureViewActivity.class));
                break;
            case R.id.surface_view:
                startActivity(new Intent(this,SurfaceViewActivity.class));
                break;
            case R.id.ffmpeg:
                startActivity(new Intent(this,FFmpegActivity.class));
                break;
            case R.id.open_camera:
                startActivity(new Intent(this,CameraActivity.class));
                break;
        }
    }
}
