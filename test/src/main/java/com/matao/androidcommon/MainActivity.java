package com.matao.androidcommon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.matao.common.util.AccessibilityUtils;
import com.matao.common.util.ScreenUtils;
import com.matao.common.util.ViewConfigurationUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mTurnOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTurnOn = (Button) findViewById(R.id.bt_turn_on);
        mTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccessibilityUtils.enableAccess(MainActivity.this);
            }
        });

        Log.d(TAG, ScreenUtils.getScreenHeight(this) + "");
        Log.d(TAG, ViewConfigurationUtils.hasPermanentMenuKey(this) + "");
    }
}
