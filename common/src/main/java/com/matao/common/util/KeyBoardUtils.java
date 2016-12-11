package com.matao.common.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by matao on 2016-12-11 16:32
 * <p/>
 * 键盘弹出、收起工具类
 */
public class KeyBoardUtils {
    private KeyBoardUtils() {
        throw new AssertionError("KeyBoardUtils cannot be instantiated!");
    }

    /**
     * 打开软键盘
     *
     * @param view
     */
    public static void showKeyboard(final View view) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
            }

        }, 500);
    }

    /**
     * 关闭软键盘
     *
     * @param view
     */
    public static void hideKeyboard(View view) {
        if (view != null && view.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
