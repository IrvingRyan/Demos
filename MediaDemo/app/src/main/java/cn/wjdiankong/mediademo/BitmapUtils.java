package cn.wjdiankong.mediademo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;

public final class BitmapUtils {
	
	@SuppressLint("SimpleDateFormat")
	public static Bitmap addTimeToBitmap(Bitmap src, long time){
		// ����һ���µĺ�SRC���ȿ��һ����λͼ
		Bitmap newb= Bitmap.createBitmap(src.getWidth(), src.getHeight(), 
				Config.ARGB_8888);
        Canvas canvas = new Canvas(newb);
        
        canvas.drawBitmap(src, 0, 0, new Paint());
        
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(36);
        
        /*SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = format.format(new Date(time));*/
        String timeStr = "���ںţ�Android��������";
        
        int strWidth = getStringWidth(paint, timeStr);
        int strHeight = getStringHeight(paint, timeStr);
        int startX = newb.getWidth() - strWidth;
        int startY = newb.getHeight() - strHeight + 36;
        
        canvas.drawText(timeStr, startX, startY, paint);
        
        return newb;
	}

	private static int getStringWidth(Paint paint, String str) {
		return (int) paint.measureText(str);
	}

	private static int getStringHeight(Paint paint, String str) {
		FontMetrics fr = paint.getFontMetrics();
		//ceil() ������������Ϊ��ӽ���������
		return (int) Math.ceil(fr.descent -fr.top); 
	}
	
	public static Bitmap rotateBitmap(Bitmap bitmap, int degree){
        Matrix matrix = new Matrix(); 
        matrix.postRotate(degree);        /*��ת180��*/
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return bitmap;
	}
}
