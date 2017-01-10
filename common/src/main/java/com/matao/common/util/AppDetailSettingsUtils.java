package com.matao.common.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by matao on 2016-12-15 11:44
 */

public class AppDetailSettingsUtils {
    private AppDetailSettingsUtils() {
        throw new UnsupportedOperationException("AppDetailSettingsUtils cannot be instantiated!");
    }

    public static void openAppDetailSetting(Context context, String packageName) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromParts("package", packageName, null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
