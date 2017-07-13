package com.aohanyao.bannerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.aohanyao.bannerlib.BannerView;
import com.aohanyao.bannerlib.inf.BannerClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BannerView bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bannerView = (BannerView) findViewById(R.id.banner);
        initBanner();
    }

    private void initBanner() {
        List<String> datas = new ArrayList<>();
        datas.add("http://admin.ailieji.com//images/product/20160825092505768.png");
        datas.add("http://admin.ailieji.com//images/product/20160824090515950.png");
        datas.add("http://admin.ailieji.com//images/product/20160824090601354.png");
        datas.add("http://admin.ailieji.com//images/product/20160810153311381.png");
        datas.add("http://admin.ailieji.com//images/product/20160810153413251.png");
        bannerView.setData(datas, new SampleBannerHelper());
        bannerView.startBanner();
        bannerView.setBannerClickListener(new BannerClickListener() {
            @Override
            public void onBannerClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "点击位置：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
