package com.matao.androidcommon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_go_to_clean_process)
    void onClickGoToCleanProcess() {
        startActivity(new Intent(MainActivity.this, CleanProcessActivity.class));
    }

    @OnClick(R.id.bt_go_to_lru_cache_demo)
    void onClickGoToLruCacheDemo() {
        startActivity(new Intent(MainActivity.this, LruCacheDemoActivity.class));
    }
}
