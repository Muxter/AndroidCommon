package com.matao.common.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.matao.common.R;

/**
 * Created by matao on 2016-12-14 15:44
 */

public class AutoInstallAccessibilityService extends BaseAccessibilityService {

    /**
     * 页面变化回调事件
     *
     * @param event event.getEventType()    当前事件的类型
     *              event.getClassName()    当前类的名称
     *              event.getSource()       当前页面中的节点信息;
     *                                      并不是所有的事件都能通过getSource()方法获取到事件源节点信息,
                                            比如像通知消息类型的事件(TYPE_NOTIFICATION_STATE_CHANGED).
     *              event.getPackageName()  事件源所在的包名
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getSource() != null
                && event.getPackageName().equals("com.android.packageinstaller")) {

            AccessibilityNodeInfo installNodeInfo = findViewByText(getString(R.string.install), true);
            if (installNodeInfo != null) {
                performViewClick(installNodeInfo);
                return;
            }

            AccessibilityNodeInfo nextNodeInfo = findViewByText(getString(R.string.next), true);
            if (nextNodeInfo != null) {
                performViewClick(nextNodeInfo);
                return;
            }

            AccessibilityNodeInfo doneNodeInfo = findViewByText(getString(R.string.done), true);
            if (doneNodeInfo != null) {
                performViewClick(doneNodeInfo);
                return;
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
