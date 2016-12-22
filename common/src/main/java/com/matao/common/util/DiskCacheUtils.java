package com.matao.common.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by matao on 2016-12-23 00:19
 */

public class DiskCacheUtils {

    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
}
