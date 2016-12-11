package com.matao.common.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by matao on 2016-12-11 16:45
 */

public class PhoneUtils {
    public static void callSysPhoneUI(Context context, String phone) {
        try {
            Intent intent = new Intent();// 打开系统拨打电话界面
            intent.setAction("android.intent.action.DIAL");
            intent.setData(Uri.parse("tel:" + phone));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "无法拨打电话！", Toast.LENGTH_SHORT).show();
//            Toasts.shortToast(context, "无法拨打电话！");
        }
    }

    public static void callPhone(Context context, String phone) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            context.startActivity(intent); // 直接拨打电话
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "无法拨打电话！", Toast.LENGTH_SHORT).show();
//            Toasts.shortToast(context, "无法拨打电话！");
        }

    }

    public static void sendMail(Context context, String email) {
        Uri uri = Uri.parse("mailto:" + email);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分");
        intent.putExtra(Intent.EXTRA_TEXT, "这是邮件的正文部分");
        context.startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
    }

    public static boolean isMobilePhone(String mobilePhone) {
        String regExp = "^[1][0-9]{10}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobilePhone);
        return m.find();
    }

    public static boolean isPhone(String phone) {
        String regExp = "^[1-9]{1}[0-9]{5,8}$";
        Pattern p = null;
        Matcher m = null;
        p = Pattern.compile(regExp);
        m = p.matcher(phone);
        return m.matches();
    }
}
