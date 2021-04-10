package com.gzc.chapter2.juc.sample2;

import java.util.List;
import java.util.Random;

public class WriterThread extends Thread {
    private final List<Integer> list;
    private final Random random = new Random();

    public WriterThread(List<Integer> list) {
        super("WriterThread");
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            list.add(i);
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.remove(0);
        }
    }

}
