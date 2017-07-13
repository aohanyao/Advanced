package com.example.test_webview_demo;

import com.tencent.smtt.sdk.QbSdk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Process;

public class PreInitBackgroundActivity extends Activity{

	protected void onCreate(android.os.Bundle savedInstanceState) {
		
		QbSdk.PreInitCallback initCallback = new QbSdk.PreInitCallback() {
			
			@Override
			public void onViewInitFinished() {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onCoreInitFinished() {
				// TODO Auto-generated method stub
				
			}
		};
		
		QbSdk.preInit(this, initCallback);
	};
	
}
