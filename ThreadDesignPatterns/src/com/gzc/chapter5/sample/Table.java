package com.gzc.chapter5.sample;

public class Table {
    private final String[] buffer;
    private int tail;  // 下次put的位置
    private int head;  // 下次take的位置
    private int curCount; // buffer中的蛋糕个数
    private int id;

    public Table(int maxCount) {
        this.buffer = new String[maxCount];
        this.head = 0;
        this.tail = 0;
        this.curCount = 0;
    }

    private int nextId() {
        id++;
        return id;
    }

    /**
     * 放置蛋糕
     * @param threadName
     * @throws InterruptedException
     */
    public synchronized void put(String threadName) throws InterruptedException {
        while (curCount >= buffer.length) {
            wait();
        }
        String cake = "[ Cake No." + nextId() + " by " + threadName + " ]"; //注意，放蛋糕要放在while循环后面
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        buffer[tail] = cake;
        tail = (tail + 1) % buffer.length;
        curCount++;
        notifyAll();
    }

    /**
     * 取蛋糕
     * @return
     * @throws InterruptedException
     */
    public synchronized String take() throws InterruptedException {
        while (curCount <= 0) {
            wait();
        }
        String cake = buffer[head];
        head = (head + 1) % buffer.length;
        curCount--;
        notifyAll();
        System.out.println("-------------" + Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }
}
