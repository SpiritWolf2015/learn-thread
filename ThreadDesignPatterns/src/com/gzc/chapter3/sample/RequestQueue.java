package com.gzc.chapter3.sample;

import java.util.Queue;
import java.util.LinkedList;

public class RequestQueue {
    private final Queue<Request> queue = new LinkedList<>();

    public synchronized Request getRequest() {
        /*
        多线程的情况下，单个生产者和单个消费者用if判断是否await不会出错；当有多个消费者的时候，还使用if判断是否await就会产生问题，
        因为一个生产者-消费者模型的任务队列，一个生产者放入任务后用notifyAll通知多个消费者，但是并非所有被唤醒的消费者都能取到一个任务，
        那么队列被读空了之后的消费者肯定得继续await。如果你用if来判断，这个消费者第二次被notify的时候就不会再次判断这个条件了，
        如果这个时候这个消费者又一次没抢到任务，但是代码还是往下执行了，轻则空指针异常，重了干出什么事情来都说不定了。
        所以必须用while来检查，这样可以保证消费线程每次被唤醒后都检查一次条件是否满足了。
        */
        while (queue.peek() == null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return queue.remove();
    }

    public synchronized void putRequest(Request request) {
        queue.offer(request);
        notifyAll();
    }
}
