package com.matao.androidcommon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.matao.common.util.AccessibilityUtils;
import com.matao.common.util.AppDetailSettingsUtils;
import com.matao.common.util.ScreenUtils;
import com.matao.common.util.ViewConfigurationUtils;

public class CleanProcessActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mTurnOn;
    private Button mClean;
    private String[] mPackages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_process);

        mPackages = new String[]{"com.imdada.bdtool"};

        mTurnOn = (Button) findViewById(R.id.bt_turn_on);
        mTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccessibilityUtils.enableAccess(CleanProcessActivity.this);
            }
        });

        mClean = (Button) findViewById(R.id.bt_clean);
        mClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanProcess(view);
            }
        });

        Log.d(TAG, ScreenUtils.getScreenHeight(this) + "");
        Log.d(TAG, ViewConfigurationUtils.hasPermanentMenuKey(this) + "");
    }

    private void cleanProcess(View view) {
        for (String mPackage : mPackages) {
            AppDetailSettingsUtils.openAppDetailSetting(this, mPackage);
        }
    }

}
