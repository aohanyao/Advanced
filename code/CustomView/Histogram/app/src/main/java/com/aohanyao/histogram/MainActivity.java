package com.aohanyao.histogram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aohanyao.histogram.lib.WeekHistogram;

public class MainActivity extends AppCompatActivity {

    private WeekHistogram weekHistogram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weekHistogram = (WeekHistogram) findViewById(R.id.wh);
    }

    public void readLoad(View view){
        weekHistogram.readLoad();
    }
}
