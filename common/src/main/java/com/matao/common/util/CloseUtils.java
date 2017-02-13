package com.matao.common.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by matao on 2017-02-13 23:34
 */

public class CloseUtils {

    private CloseUtils() {
        throw new UnsupportedOperationException("CloseUtils cannot be instantiated!");
    }

    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
