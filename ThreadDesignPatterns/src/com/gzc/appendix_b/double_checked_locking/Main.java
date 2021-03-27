package com.gzc.appendix_b.double_checked_locking;

public class Main {
    public static void main(String[] args) {
        // 线程A
        new Thread(() -> System.out.println(MySystem.getInstance().getDate())).start();

        // 线程B
        new Thread(() -> System.out.println(MySystem.getInstance().getDate())).start();
    }
}
