package com.matao.androidcommon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.matao.common.util.ScreenUtils;
import com.matao.common.util.ViewConfigurationUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, ScreenUtils.getScreenHeight(this) + "");
        Log.d(TAG, ViewConfigurationUtils.hasPermanentMenuKey(this) + "");
    }
}
