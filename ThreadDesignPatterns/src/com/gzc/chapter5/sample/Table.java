package com.gzc.chapter5.sample;

public class Table {
    private final String[] buffer;
    private int tail;  // 下次put的位置
    private int head;  // 下次take的位置
    private int count; // buffer中的蛋糕个数
    private int id;

    public Table(int count) {
        this.buffer = new String[count];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public int nextId() {
        id++;
        return id;
    }

    /**
     * 放置蛋糕
     * @param threadName
     * @throws InterruptedException
     */
    public synchronized void put(String threadName) throws InterruptedException {
        while (count >= buffer.length) {
            wait();
        }
        String cake = "[ Cake No." + nextId() + " by " + threadName + " ]"; //注意，放蛋糕要放在while循环后面
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        buffer[tail] = cake;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();
    }

    /**
     * 取蛋糕
     * @return
     * @throws InterruptedException
     */
    public synchronized String take() throws InterruptedException {
        while (count <= 0) {
            wait();
        }
        String cake = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        System.out.println("-------------" + Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }
}
