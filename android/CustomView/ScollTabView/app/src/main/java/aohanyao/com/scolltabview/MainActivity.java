package aohanyao.com.scolltabview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ScrollLevitateTabView.OnScrollLintener {
    //外层的ScrollView
    private ScrollLevitateTabView sltv;
    //真正的Tab
    private TextView flow_tab;
    //占位的Tab
    private TextView flow_tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flow_tab = (TextView) findViewById(R.id.flow_tab);
        flow_tab2 = (TextView) findViewById(R.id.flow_tab2);
        sltv = (ScrollLevitateTabView) findViewById(R.id.sltv);
        //设置监听
        sltv.setOnScrollLintener(this);
    }

    @Override
    public void onScroll(int scrollY) {
        //layout Tab的位置
        int top = Math.max(scrollY, flow_tab2.getTop());
        flow_tab.layout(0, top, flow_tab.getWidth(), top + flow_tab.getHeight());
    }
}
