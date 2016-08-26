package aohanyao.com.de;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvDp2px;
    private EditText etDp2px;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvDp2px = (TextView) findViewById(R.id.tv_px2dp);
        etDp2px = (EditText) findViewById(R.id.et_px2dp);
    }

    public void px2dp(View view) {
        String toString = etDp2px.getText().toString();
        if (!TextUtils.isEmpty(toString)) {
            float parseInt = Float.parseFloat(toString);
            float i = DensityUtils.px2dp(MainActivity.this, parseInt);
            tvDp2px.setText("PX:" + toString + "\nDP:" + i);
            etDp2px.setText("");
        }
    }
}
