package com.github.ir.camerademo.util;

import android.hardware.Camera;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yanwentao on 2017/3/23.
 */

public class SizeUtil {

    public static Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) h / w;

        if (sizes == null)
            return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.height / size.width;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;

            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }

        return optimalSize;
    }

    public static Camera.Size getLargerSize(List<Camera.Size> sizes,int imageWidth,int imageHeight) {
        // Sort the list in ascending order
        Collections.sort(sizes, new Comparator<Camera.Size>() {

            public int compare(final Camera.Size a, final Camera.Size b) {
                return a.width * a.height - b.width * b.height;
            }
        });

        // Pick the first preview size that is equal or bigger, or pick the last (biggest) option if we cannot
        // reach the initial settings of imageWidth/imageHeight.
        for (int i = 0; i < sizes.size(); i++) {
            if ((sizes.get(i).width >= imageWidth && sizes.get(i).height >= imageHeight) || i == sizes.size() - 1) {
                return sizes.get(i);
            }
        }
        return null;
    }
}
