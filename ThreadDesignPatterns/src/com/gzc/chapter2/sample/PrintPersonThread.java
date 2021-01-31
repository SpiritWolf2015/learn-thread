package com.gzc.chapter2.sample;

public class PrintPersonThread extends Thread {
    private final Person person;

    public PrintPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " prints " + person);
        }
    }
}
