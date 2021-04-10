package com.gzc.chapter2.juc.sample2;

import java.util.List;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        final List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        new WriterThread(list).start();
        new ReaderThread(list).start();
    }
}
