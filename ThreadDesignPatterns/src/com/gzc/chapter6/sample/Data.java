package com.gzc.chapter6.sample;

import java.util.Arrays;

public class Data {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public Data(int size) {
        this.buffer = new char[size];
        Arrays.fill(buffer, '*');
    }

    public char[] read() throws InterruptedException {
        // 加锁函数一定要紧贴try语句块调用，中间不要再写其他代码
        lock.readLock();
        try {
            return doRead();
        } finally {
            // 解锁函数一定要放在finally里
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        // 加锁函数一定要紧贴try语句块调用，中间不要再写其他代码
        lock.writeLock();
        try {
            doWrite(c);
        } finally {
            // 解锁函数一定要放在finally里
            lock.writeUnlock();
        }
    }

    private char[] doRead() {
        char[] newBuf = new char[buffer.length];
        System.arraycopy(buffer, 0, newBuf, 0, buffer.length);
        slowly();
        return newBuf;
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            // 每次循环里让线程休眠一下，意思是假定线程在写操作花费的时间比读操作更长
            slowly();
        }
    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // NONE OP
        }
    }

}
