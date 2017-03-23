package com.github.ir.camerademo.model;

import android.content.Context;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder;

import java.io.File;
import java.nio.ByteBuffer;

/**
 * Created by yanwentao on 2017/3/23.
 */

public class VideoProcessor {
    final private static int FRAME_RATE = 30;
    private Context context;

    private int sampleAudioRateInHz = 44100;
    private int imageWidth = 320;
    private int imageHeight = 240;
    private static VideoProcessor instance;
    private FFmpegFrameRecorder mRecorder;

    public static boolean recording=false;
    private Frame yuvImage;
    private long startTime;

    public static VideoProcessor getInstance(Context context) {
        if (instance == null)
            synchronized (VideoProcessor.class) {
                if (instance == null)
                    instance = new VideoProcessor(context);
            }
        return instance;
    }

    private VideoProcessor(Context context) {
       this.context=context;
    }
    //初始化
    private void initRecorder(File file) {
        mRecorder = new FFmpegFrameRecorder(file,imageWidth, imageHeight,2);
//        mRecorder.setInterleaved(true);
        mRecorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        mRecorder.setFormat("flv");
        mRecorder.setSampleRate(sampleAudioRateInHz);
        // Set in the surface changed method
        mRecorder.setFrameRate(FRAME_RATE);
        if (yuvImage==null)
            yuvImage = new Frame(imageWidth, imageHeight, Frame.DEPTH_UBYTE, 2);

    }

    /**
     * step 1
     */
    public void startRecorder(){
        File filesDir = new File(context.getApplicationContext().getFilesDir().getAbsoluteFile(),"/demovideo");
        initRecorder(filesDir);
        recording=true;
        startTime = System.currentTimeMillis();

    }

    /**
     * step 3
     */
    public void stopRecording(){
        recording=false;
    }

    /**
     * step 2
     * @param data
     */
    public void recording(byte[] data){
        try {
            if (recording){
                ((ByteBuffer) yuvImage.image[0].position(0)).put(data);
                long t = 1000 * (System.currentTimeMillis() - startTime);
                if (t > mRecorder.getTimestamp()) {
                    mRecorder.setTimestamp(t);
                }
                mRecorder.record(yuvImage);
            }
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        }

    }
}
