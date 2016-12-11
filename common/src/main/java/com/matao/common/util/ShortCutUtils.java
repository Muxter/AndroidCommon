package com.matao.common.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by matao on 2016-12-11 16:46
 */

public class ShortCutUtils {
    /**
     * 是否添加了快捷键
     * <p>
     * 需要权限 :<uses-permission android:name="com.android.launcher.permission.READ_SETTINGS"/>
     *
     * @param context context
     * @param appName app名称
     * @return
     */
    public static boolean isAddShortcut(Context context, String appName) {
        int version = android.os.Build.VERSION.SDK_INT;
        String AUTHORITY;

        // 2.2以上的系统的文件文件名字不同
        if (version >= 8) {
            AUTHORITY = "com.android.launcher2.settings";
        } else {
            AUTHORITY = "com.android.launcher.settings";
        }

        boolean isShortcutInstalled = false;
        final ContentResolver contentResolver = context.getContentResolver();
        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites?notify=true");
        Cursor cursor = contentResolver.query(CONTENT_URI,
                new String[]{"title", "iconResource"},
                "title=?",
                new String[]{appName},
                null);

        if (cursor != null && cursor.getCount() > 0) {
            isShortcutInstalled = true;
        }
        return isShortcutInstalled;
    }

    /**
     * 是否添加了快捷键
     * <p>
     * 需要权限 :<uses-permission android:name="com.android.launcher.permission.READ_SETTINGS"/>
     *
     * @param context    context
     * @param appNameRes app名称对应的字符串Id
     * @return
     */
    public static boolean isAddShortcut(Context context, int appNameRes) {
        String appName = context.getResources().getString(appNameRes);
        return isAddShortcut(context, appName);
    }

    /**
     * 添加快捷键
     * <p>
     * 1、可以先调用isAddShortcut判断是否已经添加快捷方式<br/>
     * 2、在启动的activity中加一个intent-filter
     * <intent-filter>
     * <action android:name="android.intent.action.CREATE_SHORTCUT" />
     * </intent-filter> <br>
     * 3、需要权限 : <uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT"/>
     *
     * @param context
     * @param cls
     * @param iconResId
     */
    public static void addShortcut(Context context, Class<?> cls, int iconResId, int appNameRes) {
        // where this is a context (e.g. your current activity)
        final Intent shortcutIntent = new Intent(context, cls);
        shortcutIntent.setAction("com.dada.mobile.android");
        final Intent intent = new Intent();
        intent.putExtra("duplicate", false);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // Sets the custom shortcut's title
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(appNameRes));
        // Set the custom shortcut icon
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(context, iconResId));
        // add the shortcut
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(intent);

        // Intent shortcut = new Intent(
        // "com.android.launcher.action.INSTALL_SHORTCUT");
        // // 设置属性
        // shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getResources()
        // .getString(appNameRes));
        // ShortcutIconResource iconRes =
        // Intent.ShortcutIconResource.fromContext(
        // context.getApplicationContext(), iconResId);
        // shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON, iconRes);
        //
        // // 是否允许重复创建
        // shortcut.putExtra("duplicate", false);
        //
        // // 设置桌面快捷方式的图标
        // Parcelable icon = Intent.ShortcutIconResource.fromContext(context,
        // iconResId);
        // shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        //
        // // 点击快捷方式的操作
        // Intent intent = new Intent(Intent.ACTION_MAIN);
        // intent.setAction("com.android.action.dada");
        // intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        // intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        // intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // intent.setClass(context, clzz);
        //
        // // 设置启动程序
        // shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        //
        // // 广播通知桌面去创建
        // context.sendBroadcast(shortcut);
    }

    /**
     * 删除程序的快捷方式
     */
    public static void delShortcut(Activity context, Class<?> cls, int iconResId, int appNameRes) {
        // Intent shortcut = new
        // Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        // // 快捷方式的名称
        // shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
        // activity.getString(appNameRes));
        // // 指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
        // // 注意: ComponentName的第二个参数必须是完整的类名（包名+类名），否则无法删除快捷方式
        // String appClass = activity.getPackageName() + "." +
        // activity.getLocalClassName();
        // DevUtil.d("zqt", "appClass="+appClass);
        // ComponentName comp = new ComponentName(activity.getPackageName(),
        // appClass);
        // shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new
        // Intent(Intent.ACTION_MAIN).setComponent(comp));
        // activity.sendBroadcast(shortcut);
        // where this is a context (e.g. your current activity)
        final Intent shortcutIntent = new Intent(context, cls);
        shortcutIntent.setAction("com.dada.mobile.android");
        final Intent intent = new Intent();
        intent.putExtra("duplicate", false);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // Sets the custom shortcut's title
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(appNameRes));
        // Set the custom shortcut icon
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(context, iconResId));
        // add the shortcut
        intent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        context.sendBroadcast(intent);
    }
}
