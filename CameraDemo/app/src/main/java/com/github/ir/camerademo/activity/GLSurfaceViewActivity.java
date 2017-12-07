package com.github.ir.camerademo.activity;

import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.github.ir.camerademo.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yanwentao on 2017/7/18.
 */

public class GLSurfaceViewActivity extends BaseActivity implements GLSurfaceView.Renderer {

    private GLSurfaceView glSurfaceView;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_surface);
        glSurfaceView = $(R.id.gl_surface);
        glSurfaceView.setRenderer(this);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mCamera = Camera.open();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }
}
