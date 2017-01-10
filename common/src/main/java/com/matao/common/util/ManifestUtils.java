package com.matao.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by matao on 2016-12-11 16:44
 */

public class ManifestUtils {

    private ManifestUtils() {
        throw new UnsupportedOperationException("ManifestUtils cannot be instantiated!");
    }

    public static Object getMetaData(Context context, String keyName) {
        try {
            ApplicationInfo appi = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            return appi.metaData.get(keyName);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
