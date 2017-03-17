package com.github.ir.camerademo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Camera.PreviewCallback,SurfaceHolder.Callback{

    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private int height=720;
    private int weight=720;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        imageView = (ImageView) findViewById(R.id.image);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setFixedSize(height, weight); // 预览大小設置 这个很重要 如果大小不对 很可能无法显示
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(this);

    }

    public void initCamera(SurfaceHolder holder){
        try {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            int numberOfCameras = Camera.getNumberOfCameras();
            Log.i("numberOfCameras","numberOfCameras = "+numberOfCameras);
            for (int i=0;i<numberOfCameras;i++){
                Camera.getCameraInfo(i,cameraInfo);
                Log.i("cameraInfo"," facing = "+cameraInfo.facing+" orientation ="+cameraInfo.orientation+" canDisableShutterSound ="+cameraInfo.canDisableShutterSound);
            }

            camera = Camera.open(0);
//            camera.setPreviewDisplay(holder);
            //设置摄像头的参数
            Camera.Parameters parameters = camera.getParameters();
            parameters.setPreviewSize(height,weight);
            parameters.setPictureSize(height,weight);
            parameters.setPreviewFormat(ImageFormat.NV21);
            camera.setDisplayOrientation(0);
//            parameters.setRotation(90);

            //获取摄像头支持的数据格式
            List<Integer> supportedPreviewFormats = parameters.getSupportedPreviewFormats();
            Log.i(""," supportedPreviewFormats  = "+supportedPreviewFormats.toString());

            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            for (Camera.Size size:supportedPreviewSizes){
                Log.i("supportedPreviewSizes"," height:"+size.height +" width:"+size.width);
            }

            List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
            for (Camera.Size size:supportedPictureSizes){
                Log.i("supportedPictureSizes"," height:"+size.height +" width:"+size.width);

            }

            camera.setParameters(parameters);
            camera.setPreviewCallback(this);
            camera.startPreview();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void destoryCamera(){
        if (camera!=null){
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera=null;
        }
    }
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size previewSize = parameters.getPreviewSize();
        //以下操作将byte数据转换为bitmap 并显示
        YuvImage image=new YuvImage(data,ImageFormat.NV21,previewSize.width,previewSize.height,null);
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        image.compressToJpeg(new Rect(0,0,previewSize.width,previewSize.height),100,stream);

        Bitmap bitmap = BitmapFactory.decodeByteArray(stream.toByteArray(), 0, stream.size());

        Bitmap timeStamp = BitmapUtills.addTimeStamp(bitmap);
        imageView.setImageBitmap(timeStamp);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initCamera(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        destoryCamera();
    }
}
