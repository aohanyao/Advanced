package com.jc.adv;

/**
 * Created by jjc on 2019/1/10.
 */
public class HandlerThread extends Thread {
    private Looper looper;

    @Override
    public void run() {
        // 准备
        Looper.prepare();
        // 获取
        looper = Looper.myLooper();
        //开始执行，这里就已经是卡住了
        System.out.print("looper");
        looper.looper();

    }


    public void post(Runnable task) {
        looper.setTask(task);
    }

}
