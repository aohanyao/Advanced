package aohanyao.com.eventbussample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import aohanyao.com.eventbussample.event.UpdateUiEvent;

public class LoginActivity extends AppCompatActivity {
    private EditText mEtMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtMsg = (EditText) findViewById(R.id.et_msg);
    }
    public void login(View view) {
        Toast.makeText(this, mEtMsg.getText().toString(), Toast.LENGTH_SHORT).show();
        //发送事件
        EventBus.getDefault().post(new UpdateUiEvent(mEtMsg.getText().toString()));
        finish();
    }
}
