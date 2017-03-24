package com.github.ir.camerademo.model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder;

import java.io.File;
import java.nio.ByteBuffer;

/**
 * Created by yanwentao on 2017/3/23.
 */

public class VideoProcessor {
    private File ffmpeg_link = new File(Environment.getExternalStorageDirectory(), "stream.mp4");

    private String TAG="VideoProcessor";
    /* The number of seconds in the continuous record loop (or 0 to disable loop). */
    final int RECORD_LENGTH = 10;
    final private static int FRAME_RATE = 30;
    private Context context;

    private int sampleAudioRateInHz = 44100;
    public int imageWidth = 320;
    public int imageHeight = 240;
    private static VideoProcessor instance;
    private FFmpegFrameRecorder mRecorder;

    public static boolean recording=false;
    private Frame yuvImage;
    private long startTime;
    private Frame[] images;
    private long[] timestamps;
    private int index;

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
        Log.i(TAG,"imageWidth ="+imageWidth+" imageHeight ="+imageHeight);
        if (RECORD_LENGTH>0){
            index=0;
            images = new Frame[RECORD_LENGTH * FRAME_RATE];//录制长度*帧数
            timestamps = new long[images.length];
            for(int i = 0; i < images.length; i++) {
                images[i] = new Frame(imageWidth, imageHeight, Frame.DEPTH_UBYTE, 2);
                timestamps[i] = -1;
            }
        }
        mRecorder = new FFmpegFrameRecorder(file,imageWidth, imageHeight,2);
//        mRecorder.setInterleaved(true);
//        mRecorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        mRecorder.setFormat("mp4");
//        mRecorder.setSampleRate(sampleAudioRateInHz);
        // Set in the surface changed method
        mRecorder.setFrameRate(FRAME_RATE);
        if (yuvImage==null)
            yuvImage = new Frame(imageWidth, imageHeight, Frame.DEPTH_UBYTE, 2);

    }

    /**
     * step 1
     */
    public void startRecorder(){
        try {
            File filesDir = new File(Environment.getExternalStorageDirectory(),"demovideo.mp4");
            initRecorder(filesDir);
            mRecorder.start();
            recording=true;
            startTime = System.currentTimeMillis();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * step 3
     */
    public void stopRecording(){
        try {
            recording=false;
            mRecorder.stop();
            mRecorder.release();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * step 2
     * @param data
     */
    public void recording(byte[] data){
        try {
            if (recording){
                int i = index++ % images.length;
                yuvImage=images[i];
//                timestamps[i]=1000 * (System.currentTimeMillis() - startTime);
                ((ByteBuffer) yuvImage.image[0].position(0)).put(data);
//                if (t > mRecorder.getTimestamp()) {
//                    mRecorder.setTimestamp(t);
//                }
                mRecorder.record(yuvImage);
            }
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        }

    }
}
