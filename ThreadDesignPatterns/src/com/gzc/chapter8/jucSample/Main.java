package com.gzc.chapter8.jucSample;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try {
            new ClientThread("Sky", executorService).start();
            new ClientThread("Grubby", executorService).start();
            new ClientThread("Moon", executorService).start();

            // 等待大约5秒
            Thread.sleep(5L * 1000L);
        } catch (InterruptedException e) {
            // NONE OP
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }
}
