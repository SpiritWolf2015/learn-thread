package com.gzc.chapter12.sample.activeobject;

import com.gzc.chapter12.sample.activeobject.request.MethodRequest;

public class ActivationQueue {
    private static final int MAX_METHOD_REQUEST = 100;
    private final MethodRequest<?>[] requestQueue;
    private int tail;  // 下次putRequest的位置
    private int head;  // 下次takeRequest的位置
    private int count; // MethodRequest的数量

    public ActivationQueue() {
        this.requestQueue = new MethodRequest[MAX_METHOD_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public synchronized void putRequest(MethodRequest<?> request) {
        while (count >= requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                // NONE OP
            }
        }
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        notifyAll();
    }

    public synchronized MethodRequest<?> takeRequest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // NONE OP
            }
        }
        MethodRequest<?> request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }
}
