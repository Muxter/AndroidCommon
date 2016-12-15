package com.matao.common.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.matao.common.R;

/**
 * Created by matao on 2016-12-14 17:30
 */

public class CleanBackgroundProcessAccessibilityService extends BaseAccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
                event.getPackageName().equals("com.android.settings")) {
            CharSequence className = event.getClassName();
            if (className.equals("com.android.settings.applications.InstalledAppDetailsTop")) {
                AccessibilityNodeInfo info = findClickableViewByText(getString(R.string.force_stop));
                if (info != null && info.isEnabled()) {
                    performViewClick(info);
                } else {
                    performBackClick();
                }
            }
            if (className.equals("android.app.AlertDialog")) {
                performBackClick();
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
