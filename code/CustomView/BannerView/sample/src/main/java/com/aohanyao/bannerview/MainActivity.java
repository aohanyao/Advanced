package com.aohanyao.bannerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aohanyao.bannerlib.BannerView;

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
        datas.add("http://admin.ailieji.com//images/product/Top20160810101644.png");
        datas.add("http://admin.ailieji.com//images/product/Top20160809094502.png");
        datas.add("http://admin.ailieji.com//images/product/Top20160810101644.png");
        datas.add("http://admin.ailieji.com//images/product/Top20160712151049.png");
        datas.add("http://admin.ailieji.com//images/product/20160824090341148.png");
        bannerView.setData(datas, new SampleBannerHelper());
        bannerView.startBanner();
    }
}
