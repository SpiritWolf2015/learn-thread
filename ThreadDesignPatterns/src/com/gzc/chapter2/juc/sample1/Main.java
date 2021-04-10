package com.gzc.chapter2.juc.sample1;

import java.util.List;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        new WriterThread(list).start();
        new ReaderThread(list).start();
    }
}
