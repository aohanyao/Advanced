package com.jjc.reveal.effect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

public class MainActivity extends AppCompatActivity {

    private View vAnim;
    double radio;
    boolean isOpen = true;
    private Animator circularReveal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vAnim = findViewById(R.id.v_anim);
        vAnim.setVisibility(View.INVISIBLE);
    }

    public void startAnim(View v) {
        if (radio == 0L) {
            radio = Math.sqrt(Math.pow(vAnim.getWidth(), 2) + Math.pow(vAnim.getHeight(), 2));
        }
        if (circularReveal != null && circularReveal.isRunning()) return;
        if (isOpen) {
            circularReveal = ViewAnimationUtils.createCircularReveal
                    (vAnim, vAnim.getWidth()/2, vAnim.getHeight()/2, (float) radio, 0);
            //vAnim.setVisibility(View.VISIBLE);
        } else {
            circularReveal = ViewAnimationUtils.createCircularReveal
                    (vAnim, vAnim.getWidth()/2, vAnim.getHeight()/2, 0, (float) radio);
        }
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (isOpen){
                    vAnim.setVisibility(View.INVISIBLE);
                }else{

                }
                isOpen = !isOpen;
            }
        });
        vAnim.setVisibility(View.VISIBLE);
        circularReveal.setDuration(1000);
        circularReveal.start();
    }
}
