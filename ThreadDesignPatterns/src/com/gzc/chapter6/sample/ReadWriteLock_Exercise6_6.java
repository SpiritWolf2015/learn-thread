package com.gzc.chapter6.sample;

/**
 * 习题6-6的代码
 */
public final class ReadWriteLock_Exercise6_6 {
    private int readingReaders = 0; // (A)…实际正在读取中的线程个数，也就是执行了readLock，但尚未执行readUnlock的线程个数。readingReaders的值一定大于0
    private int writingWriters = 0; // (B)…实际正在写入中的线程个数，也就是执行了writeLock，但尚未执行writeUnlock的线程个数。writingWriters字段的值只能是0或1，肯定不会大于或等于2，任何时候都不会

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0) {
            wait();
        }
        readingReaders++;                       // (A) 实际正在读取的线程个数加1
    }

    public synchronized void readUnlock() {
        readingReaders--;                       // (A) 实际正在读取的线程个数减1
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        while (readingReaders > 0 || writingWriters > 0) {
            wait();
        }
        writingWriters++;                       // (B) 实际正在写入的线程个数加1
    }

    public synchronized void writeUnlock() {
        writingWriters--;                       // (B) 实际正在写入的线程个数减1
        notifyAll();
    }
}
