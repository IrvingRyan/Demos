package com.github.ir.camerademo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanwentao on 2017/3/17.
 */

public class BitmapUtills {
     static   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年 M月d日 hh:mm:ss");

    public static Bitmap addTimeStamp(Bitmap bitmap){
        Bitmap b = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        Canvas canvas = new Canvas(b);
        canvas.drawBitmap(bitmap,0,0,new Paint());
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(12);
        canvas.drawText(format,20,20,paint);
        return b;
    }
}
