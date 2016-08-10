package aohanyao.com.eventbussample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import aohanyao.com.eventbussample.event.UpdateUiEvent;

public class NoLoginActivity extends AppCompatActivity {
    private TextView mTvMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_login1);
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
        EventBus.getDefault().register(this);//注册
        mTvMsg.append("  当前页面："+hashCode()+" \n");
    }
    public void startUpdateUI(View view) {
        startActivity(new Intent(this, NoLoginActivity.class));
    }
    public void goLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
    @Subscribe
    public void onEventMainThread(UpdateUiEvent uiEvent) {
        mTvMsg.setText("  当前页面："+hashCode()+"\n信息："+uiEvent.getMs());//接收到事件  将信息设置到页面上
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);//取消注册
        super.onDestroy();
    }
}
