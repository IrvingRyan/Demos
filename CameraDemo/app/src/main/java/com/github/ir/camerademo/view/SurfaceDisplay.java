package com.github.ir.camerademo.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.github.ir.camerademo.util.BitmapUtills;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yanwentao on 2017/3/21.
 */

public class SurfaceDisplay extends SurfaceView implements SurfaceHolder.Callback,
        Camera.PreviewCallback {
    public final static String TAG = "GameDisplay";

    private static final int MAGIC_TEXTURE_ID = 10;
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 480;
    public static final int BLUR = 0;
    public static final int CLEAR = BLUR + 1;
    //public static final int PAUSE = PLAY + 1;
    //public static final int EXIT = PAUSE + 1;
    public SurfaceHolder gHolder;
    public SurfaceTexture gSurfaceTexture;
    public Camera gCamera;
    public byte gBuffer[];
    public int textureBuffer[];
    //    public ProcessThread gProcessThread;
    private int bufferSize;
    private Camera.Parameters parameters;
    public int previewWidth, previewHeight;
    public int screenWidth, screenHeight;
    public Bitmap gBitmap;
    private Rect gRect;
    // timer
    private Timer sampleTimer;
    private TimerTask sampleTask;

    public SurfaceDisplay(Context context, int screenWidth, int screenHeight) {
        super(context);
        gHolder = this.getHolder();
        gHolder.addCallback(this);
        gHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        gSurfaceTexture = new SurfaceTexture(MAGIC_TEXTURE_ID);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        gRect = new Rect(0, 0, screenWidth, screenHeight);
        Log.v(TAG, "GameDisplay initialization completed");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        Log.v(TAG, "GameDisplay surfaceChanged");
        parameters = gCamera.getParameters();
        List<Size> preSize = parameters.getSupportedPreviewSizes();
        previewWidth = preSize.get(0).width;
        previewHeight = preSize.get(0).height;
        for (int i = 1; i < preSize.size(); i++) {
            double similarity = Math
                    .abs(((double) preSize.get(i).height / screenHeight)
                            - ((double) preSize.get(i).width / screenWidth));
            if (similarity < Math.abs(((double) previewHeight / screenHeight)
                    - ((double) previewWidth / screenWidth))) {
                previewWidth = preSize.get(i).width;
                previewHeight = preSize.get(i).height;
            }
        }
        gBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
        parameters.setPreviewSize(previewWidth, previewHeight);
        gCamera.setParameters(parameters);
        bufferSize = previewWidth * previewHeight;
        textureBuffer = new int[bufferSize];
        bufferSize = bufferSize * ImageFormat.getBitsPerPixel(parameters.getPreviewFormat()) / 8;
        gBuffer = new byte[bufferSize];
        gCamera.addCallbackBuffer(gBuffer);
        gCamera.setPreviewCallbackWithBuffer(this);
        gCamera.startPreview();
        //gProcessThread = new ProcessThread(surfaceView,handler,null,previewWidth,previewHeight);
        //processThread.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.v(TAG, "GameDisplay surfaceCreated");

        try {
            if (gCamera == null) {
                gCamera = Camera.open();
            }
            gCamera.setPreviewTexture(gSurfaceTexture);
            //sampleStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.v(TAG, "GameDisplay surfaceDestroyed");
        //gProcessThread.isRunning=false;
        //sampleTimer.cancel();
        //sampleTimer = null;
        //sampleTask.cancel();
        //sampleTask = null;
        gCamera.stopPreview();
        gCamera.release();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.v(TAG, "GameDisplay onPreviewFrame");
        //gProcessThread.raw_data=data;
        camera.addCallbackBuffer(gBuffer);
        for (int i = 0; i < textureBuffer.length; i++)
            textureBuffer[i] = 0xff000000 | data[i];
        gBitmap.setPixels(textureBuffer, 0, previewWidth, 0, 0, previewWidth, previewHeight);
        Canvas canvas = this.getHolder().lockCanvas();
        Bitmap timeStamp = BitmapUtills.addTimeStamp(gBitmap);
        if (canvas!=null){
            canvas.drawBitmap(timeStamp, null, gRect, null);
            //canvas.drawBitmap(textureBuffer, 0, screenWidth, 0, 0, screenWidth, screenHeight, false, null);
            this.getHolder().unlockCanvasAndPost(canvas);
        }

    }

    public void sampleStart() {
        Log.v(TAG, "GameDisplay sampleStart");
        sampleTimer = new Timer(false);
        sampleTask = new TimerTask() {
            @Override
            public void run() {
//                gProcessThread.timer=true;
            }
        };
        sampleTimer.schedule(sampleTask, 0, 80);
    }
}
