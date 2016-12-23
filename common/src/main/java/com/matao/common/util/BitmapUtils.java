package com.matao.common.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by matao on 2016-12-23 10:54
 */

public class BitmapUtils {

    /**
     * 将任意图片压缩成 reqWidth * reqHeight 的缩略图
     *
     * @param res
     * @param resId
     * @param reqWidth  图片期望的宽度
     * @param reqHeight 图片期望的高度
     * @return  bitmap
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);
        return bitmap;
    }


    /**
     * 计算图片在期望宽高下的采样率
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return  inSampleSize
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            int halfWidth = width / 2;
            int halfHeight = height / 2;
            // 保证最终图片的宽和高一定都会大于等于目标的宽和高。
            // 此处采样率为最接近的一个2的整数次方。这是建议实现方式。
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
