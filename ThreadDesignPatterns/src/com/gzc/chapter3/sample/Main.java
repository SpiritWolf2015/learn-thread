package com.gzc.chapter3.sample;

public class Main {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "th000", 123L).start();
//        new ClientThread(requestQueue, "ted", 111L).start();
//        new ClientThread(requestQueue, "infi", 222L).start();

        new ServerThread(requestQueue, "Spirit_wolf", 1231123123L).start();
    }
}
