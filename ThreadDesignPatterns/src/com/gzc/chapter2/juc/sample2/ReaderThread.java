package com.gzc.chapter2.juc.sample2;

import java.util.List;
import java.util.Random;

public class ReaderThread extends Thread {
    private final List<Integer> list;
    private final Random random = new Random();

    public ReaderThread(List<Integer> list) {
        super("ReaderThread");
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                System.out.println("------------start------------");
                for (int n : list) {
                    System.out.println(n);
                }
                System.out.println("------------end------------");
            }
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
