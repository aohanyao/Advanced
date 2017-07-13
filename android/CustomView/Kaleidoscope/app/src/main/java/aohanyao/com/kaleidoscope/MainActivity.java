package aohanyao.com.kaleidoscope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import aohanyao.com.kaleidoscope.ui.KaleidoscopeView;

public class MainActivity extends AppCompatActivity {

    private KaleidoscopeView kaleidoscopeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kaleidoscopeView = (KaleidoscopeView) findViewById(R.id.KaleidoscopeView);
    }

    public void startOrStop(View view) {
        kaleidoscopeView.startAnimator();
    }
}
