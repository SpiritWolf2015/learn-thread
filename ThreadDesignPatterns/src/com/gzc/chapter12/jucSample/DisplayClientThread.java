package com.gzc.chapter12.jucSample;

import com.gzc.chapter12.jucSample.activeobject.ActiveObject;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.CancellationException;

public class DisplayClientThread extends Thread {
    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                // 有返回值的调用
                String string = Thread.currentThread().getName() + " " + i;
                activeObject.displayString(string);
                Thread.sleep(200);
            }
        } catch (RejectedExecutionException | CancellationException | InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        }
    }
}
