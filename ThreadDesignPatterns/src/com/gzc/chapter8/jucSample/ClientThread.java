package com.gzc.chapter8.jucSample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.Random;

public class ClientThread extends Thread {
    private final ExecutorService executorService;
    private static final Random random = new Random();

    public ClientThread(String name, ExecutorService executorService) {
        super(name);
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                executorService.execute(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            // NONE OP
        } catch (RejectedExecutionException e) {
            // 当ExecutorService进入终止处理后，execute方法会被拒绝（reject）执行，并抛出RejectedExecutionException
            System.out.println(getName() + " : " + e);
        }
    }
}
