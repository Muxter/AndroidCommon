package com.matao.common.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by matao on 2016-12-11 16:19
 * <p>
 * ScreenUtils提供了获取屏幕宽高以及dp与px相互转换的方法
 */

public class ScreenUtils {
    private ScreenUtils() {
        throw new AssertionError("ScreenUtils cannot be instantiated!");
    }

    public static int getScreenWidth(Context context) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
