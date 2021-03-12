package com.gzc.chapter11.sample2;

public class ClientThread extends Thread {

    public ClientThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " BEGIN");

        for (int i = 0; i < 10; i++) {
            Log.println("i = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // NONE OP
            }
        }

        Log.close();
        System.out.println(getName() + " END");
    }
}
