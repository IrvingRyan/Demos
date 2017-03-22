package com.github.ir.camerademo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.ViewGroup;

import java.io.IOException;

/**
 * Created by yanwentao on 2017/3/22.
 */

public class TextureViewImp implements SurfaceTextureListener {
    private final TextureView mTextureView;
    private Camera mCamera;

    public TextureViewImp(Context context) {
        mTextureView = new TextureView(context);
        mTextureView.setSurfaceTextureListener(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ((Activity)context).setContentView(mTextureView,layoutParams);
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        try {
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewTexture(surface);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            if (mCamera!=null){
                mCamera.stopPreview();
                mCamera.release();
            }
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
