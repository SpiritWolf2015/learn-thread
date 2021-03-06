package com.gzc.chapter5.sample;

public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);     // 创建一个能放置3个蛋糕的桌子
        // OK
//        test1(table);
        // OK
        test2(table);
        // OK
//        test3(table);
        // OK
//        test4(table);
    }

    private static void test1(Table table) {
        new MakerThread("MakerThread-1", table, 31415).start();
        new MakerThread("MakerThread-2", table, 92653).start();
        new MakerThread("MakerThread-3", table, 58979).start();

        new EaterThread("EaterThread-1", table, 32384).start();
        new EaterThread("EaterThread-2", table, 62643).start();
        new EaterThread("EaterThread-3", table, 38327).start();
    }

    private static void test2(Table table) {
        new MakerThread("MakerThread-1", table, 31415).start();
        new MakerThread("MakerThread-2", table, 92653).start();
        new MakerThread("MakerThread-3", table, 58979).start();

        new EaterThread("EaterThread-1", table, 32384).start();
    }

    private static void test3(Table table) {
        new MakerThread("MakerThread-1", table, 31415).start();

        new EaterThread("EaterThread-1", table, 32384).start();
        new EaterThread("EaterThread-2", table, 62643).start();
        new EaterThread("EaterThread-3", table, 38327).start();
    }

    private static void test4(Table table) {
        new MakerThread("MakerThread-1", table, 31415).start();
        new EaterThread("EaterThread-1", table, 32384).start();
    }
}
