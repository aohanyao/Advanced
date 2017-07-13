package com.aohanyao.scroll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * layoutScroll
     */
    public void layoutScroll(View view) {
        openActivity(ToScrollActivity.class);
    }

    private void openActivity(Class<?> startClass) {
        startActivity(new Intent(this, startClass));
    }
}
