package com.gzc.chapter1.sample1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing Gate, hit CTRL+C to exit.");
        Gate gate = new Gate();

        new UserThread(gate, "Beijing_wolf", "Beijing").start();
        new UserThread(gate, "Wuhan_guozhicheng", "Wuhan").start();
        new UserThread(gate, "Alice", "Alaska").start();
        new UserThread(gate, "Bobby", "Brazil").start();
        new UserThread(gate, "Chris", "Canada").start();
    }
}
