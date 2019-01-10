package com.jc.adv;

/**
 * Created by jjc on 2019/1/10.
 */
public class Looper {
    private static final ThreadLocal<Looper> sLooperThreadLocal = new InheritableThreadLocal<>();

    Looper() {
    }

    public static void prepare() {
        if (sLooperThreadLocal.get() != null) {
            throw new RuntimeException("sLooperThreadLocal.get() != null");
        }

        sLooperThreadLocal.set(new Looper());

    }

    public static Looper myLooper() {
        return sLooperThreadLocal.get();
    }

    private Runnable task;
    private boolean isQuit;

    public synchronized void setTask(Runnable task) {
        this.task = task;
    }

    public synchronized void quit() {
        isQuit = true;
    }

    public void looper() {
        synchronized (this) {
            while (!isQuit) {
                if (task != null) {
                    task.run();
                    task = null;
                }
            }
        }
    }

}
