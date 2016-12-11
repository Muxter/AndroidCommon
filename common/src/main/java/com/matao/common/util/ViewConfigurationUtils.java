package com.matao.common.util;

import android.content.Context;
import android.view.ViewConfiguration;

/**
 * Created by matao on 2016-12-11 17:51
 * <p>
 * ViewConfigurationUtils提供了一些自定义控件用到的标准常量，比如尺寸大小，滑动距离，敏感度等等。
 */
public class ViewConfigurationUtils {
    private ViewConfigurationUtils() {
        throw new AssertionError("ViewConfigurationUtils cannot be instantiated!");
    }

    /**
     * 获取系统所能识别出的被认为是滑动的最小距离
     *
     * @param context
     * @return
     */
    public static int getScaledTouchSlop(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        return viewConfiguration.getScaledTouchSlop();
    }

    /**
     * 获取Fling速度最小值
     *
     * @param context
     * @return
     */
    public static int getScaledMinimumFlingVelocity(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        return viewConfiguration.getScaledMinimumFlingVelocity();
    }

    /**
     * 获取Fling速度最大值
     *
     * @param context
     * @return
     */
    public static int getScaledMaximumFlingVelocity(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        return viewConfiguration.getScaledMaximumFlingVelocity();
    }

    /**
     * 判断是否有物理Menu按键
     * Android3.0以后物理Menu键取消
     *
     * @param context
     * @return
     */
    public static boolean hasPermanentMenuKey(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        return viewConfiguration.hasPermanentMenuKey();
    }

    /**
     * 双击间隔时间.在该时间内是双击，否则是单击
     *
     * @return
     */
    public static int getDoubleTapTimeout() {
        return ViewConfiguration.getDoubleTapTimeout();
    }

    /**
     * 按住后，状态转变为长按状态需要的时间
     *
     * @return
     */
    public static int getLongPressTimeout() {
        return ViewConfiguration.getLongPressTimeout();
    }

    /**
     * 重复按键的间隔时间
     *
     * @return
     */
    public static int getKeyRepeatTimeout() {
        return ViewConfiguration.getKeyRepeatTimeout();
    }
}
