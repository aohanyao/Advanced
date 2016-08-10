package com.example.test_webview_demo;

import com.tencent.smtt.sdk.TbsDownloader;

import android.app.Application;

public class APPAplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
		TbsDownloader.needDownload(getApplicationContext(), false);
	}

}
