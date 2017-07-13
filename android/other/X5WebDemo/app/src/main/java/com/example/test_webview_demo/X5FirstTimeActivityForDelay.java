package com.example.test_webview_demo;

import com.example.test_webview_demo.utils.X5WebView;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.QbSdk.PreInitCallback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class X5FirstTimeActivityForDelay extends Activity{
	
	
	/**
	 * 本例展示如何第一次打开X5webview 就能加载X5内核的方法（延时构建X5webview，为preinit的网络请求争取时间）
	 * 将适用于特殊的场景
	 * 但是注意 额外的冷启动时间
	 */

	X5WebView x5WebView;
	private long timerCounter;
	private Handler handler;
	
	public static final int MSG_WEBVIEW_CONSTRUCTOR = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		this.timerCounter=System.currentTimeMillis();
		this.handler = new Handler(Looper.getMainLooper()){
			@Override
			public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
				switch(msg.what){
				case MSG_WEBVIEW_CONSTRUCTOR:
					X5FirstTimeActivityForDelay.this.doX5WebViewConstruction();
					break;
				}
			super.handleMessage(msg);
		}
		};

		
		this.preinitX5WebCore();
		
		
	}
	
	
	/**
	 * X5内核在使用preinit接口之后，对于首次安装首次加载没有效果
	 * 实际上，X5webview的preinit接口只是降低了webview的冷启动时间；
	 * 因此，现阶段要想做到首次安装首次加载X5内核，必须要让X5内核提前获取到内核的加载条件
	 */
	private void preinitX5WebCore(){


		if(!QbSdk.isTbsCoreInited()){//preinit只需要调用一次，如果已经完成了初始化，那么就直接构造view
			QbSdk.preInit(X5FirstTimeActivityForDelay.this, myCallback);//设置X5初始化完成的回调接口  第三个参数为true：如果首次加载失败则继续尝试加载；
		}else{
			handler.sendEmptyMessageDelayed(MSG_WEBVIEW_CONSTRUCTOR,500); //延时500ms的构建webview(为冷启动争取时间)
		}
	}
	
	
	
	private PreInitCallback myCallback=new QbSdk.PreInitCallback() {
		
		@Override
		public void onViewInitFinished() {//当X5webview 初始化结束后的回调
			// TODO Auto-generated method stub
			float deltaTime=(System.currentTimeMillis()-X5FirstTimeActivityForDelay.this.timerCounter)/1000;
			Toast.makeText(X5FirstTimeActivityForDelay.this, "x5初始化使用了"+deltaTime+"秒", Toast.LENGTH_LONG).show();
			Log.i("yuanhaizhou", "x5初始化使用了"+deltaTime+"秒");
			X5FirstTimeActivityForDelay.this.handler.sendEmptyMessageDelayed(MSG_WEBVIEW_CONSTRUCTOR,500);
		}
		
		@Override
		public void onCoreInitFinished() {
			// TODO Auto-generated method stub
			Log.i("yuanhaizhou","onCoreInitFinished");
		}

	
	};
	
	
	/**
	 * 使用这个方法完成webview的构造<br>
	 * 总之webview的初始化一定要放在QbSdk.preInit的X5初始化构造之后
	 * 
	 */
	private void doX5WebViewConstruction(){
		
		
		//注意：setContentView 方法会将XML的布局自动new 的方式构造，请把它 放在 X5初始化完成之后调用
		
		//第一种构造方式
		this.setContentView(R.layout.fullscreen_web);
		this.x5WebView=(X5WebView) findViewById(R.id.full_web_webview);
		this.x5WebView.loadUrl("file:///android_asset/webpage/firstX5.html");

		
		
		//或者
//		LayoutInflater layoutInflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		ViewGroup majorView=(ViewGroup) layoutInflater.inflate(R.layout.fullscreen_web, null);
//		this.setContentView(majorView);
		
		//或者
//		this.setContentView(R.layout.fullscreen_web);
//		X5WebView webView=new X5WebView(this);
//		ViewGroup group=(ViewGroup) findViewById(R.id.full_web_webview);
//		LayoutParams layoutParams=new LayoutParams(720, 1280);
//		group.addView(webView, layoutParams);
//		
	}
}
