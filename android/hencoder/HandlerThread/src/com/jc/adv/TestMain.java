package com.jc.adv;

import java.util.concurrent.Executors;

/**
 * Created by jjc on 2019/1/10.
 */
public class TestMain {
    public static void main(String[] args) {
        HandlerThread handlerThread = new HandlerThread();

        handlerThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handlerThread.post(new Runnable() {
            @Override
            public void run() {
                System.out.print("haha");
            }
        });

        
    }
}
