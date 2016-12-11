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

    public static int px2dp(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    public static int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static int px2sp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity + 0.5f);
    }

    public static int sp2px(Context context, float sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaledDensity + 0.5f);
    }
}
