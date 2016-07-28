package jjc.qxz.com.scalediagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

/**
 * 圆形比例图
 */
public class MainActivity extends AppCompatActivity {

    private ScaledDiagramView pdv;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdv = (ScaledDiagramView) findViewById(R.id.pdv);
        random = new Random();
    }
    public void start(View view){
        pdv.setMaxValue(random.nextInt(100));
    }
}
