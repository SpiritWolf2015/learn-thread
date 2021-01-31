package com.gzc.chapter2.sample;

public class Main {
    public static void main(String[] args) {
        Person alice = new Person("Spirit_wolf", "Beijing");
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
    }
}
