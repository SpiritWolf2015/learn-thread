package com.gzc.chapter5.ori_sample;

public class Table {
    private final String[] buffer;
    private int tail;  // 下次put的位置
    private int head;  // 下次take的位置
    private int curCount; // buffer中的蛋糕个数

    public Table(int maxCount) {
        this.buffer = new String[maxCount];
        this.head = 0;
        this.tail = 0;
        this.curCount = 0;
    }

    // 放置蛋糕
    public synchronized void put(String cake) throws InterruptedException {
        System.out.println("-----------------" + Thread.currentThread().getName() + " puts " + cake);
        while (curCount >= buffer.length) {
            wait();
        }
        buffer[tail] = cake;
        tail = (tail + 1) % buffer.length;
        curCount++;
        notifyAll();
    }

    // 取蛋糕
    public synchronized String take() throws InterruptedException {
        while (curCount <= 0) {
            wait();
        }
        String cake = buffer[head];
        head = (head + 1) % buffer.length;
        curCount--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }
}
