package com.gzc.chapter6.sample;

public final class ReadWriteLock {
    private int readingReaders = 0; // (A)…实际正在读取中的线程个数，也就是执行了readLock，但尚未执行readUnlock的线程个数。readingReaders的值一定大于0
    private int waitingWriters = 0; // (B)…正在等待写入的线程个数
    private int writingWriters = 0; // (C)…实际正在写入中的线程个数，也就是执行了writeLock，但尚未执行writeUnlock的线程个数。writingWriters字段的值只能是0或1，肯定不会大于或等于2，任何时候都不会
    private boolean preferWriter = true; // 若写入优先，则为true

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        }
        readingReaders++;                       // (A) 实际正在读取的线程个数加1
    }

    public synchronized void readUnlock() {
        readingReaders--;                       // (A) 实际正在读取的线程个数减1
        preferWriter = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;                       // (B) 正在等待写入的线程个数加1
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters--;                   // (B) 正在等待写入的线程个数减1
        }
        writingWriters++;                       // (C) 实际正在写入的线程个数加1
    }

    public synchronized void writeUnlock() {
        writingWriters--;                       // (C) 实际正在写入的线程个数减1
        preferWriter = false;
        notifyAll();
    }
}
