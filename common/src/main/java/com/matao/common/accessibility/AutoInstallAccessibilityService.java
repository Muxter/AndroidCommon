package com.matao.common.accessibility;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by matao on 2016-12-14 15:44
 */

public class AutoInstallAccessibilityService extends BaseAccessibilityService {

    /**
     * 页面变化回调事件
     *
     * @param event event.getEventType()    当前事件的类型；
     *              event.getClassName()    当前类的名称；
     *              event.getSource()       当前页面中的节点信息
     *              event.getPackageName()  事件源所在的包名
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // 并不是所有的事件都能通过getSource()方法获取到事件源,
        // 比如像通知消息类型的事件(TYPE_NOTIFICATION_STATE_CHANGED).
        if (event.getSource() != null
                && event.getPackageName().equals("com.android.packageinstaller")) {

            Log.d("access_matao", event.toString());

            AccessibilityNodeInfo installNodeInfo = findViewByText("安装", true);
            if (installNodeInfo != null) {
                performViewClick(installNodeInfo);
                Log.d("access_matao", "install");
                return;
            }

            AccessibilityNodeInfo nextNodeInfo = findViewByText("下一步", true);
            if (nextNodeInfo != null) {
                performViewClick(nextNodeInfo);
                Log.d("access_matao", "next");
                return;
            }

            AccessibilityNodeInfo finishNodeInfo = findViewByText("完成", true);
            if (finishNodeInfo != null) {
                performViewClick(finishNodeInfo);
                Log.d("access_matao", "click");
                return;
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
