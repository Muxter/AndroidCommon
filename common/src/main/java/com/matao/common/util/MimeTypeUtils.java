package com.matao.common.util;

import android.webkit.MimeTypeMap;

/**
 * Created by matao on 2016-12-11 16:44
 */

public class MimeTypeUtils {
    public static String getMimeType(final String fileName) {
        String result = "application/octet-stream"; // 二进制类型
        int extPos = fileName.lastIndexOf(".");
        if (extPos != -1) {
            String ext = fileName.substring(extPos + 1);
            result = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
        }
        return result;
    }
}
