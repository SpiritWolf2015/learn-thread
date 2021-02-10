package com.gzc.chapter5.jucSample1;

public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);     // 创建一个能放置3个蛋糕的桌子
        // 3个生产者，3个消费者，OK
//        test1(table);
        // 3个生产者，1个消费者，OK
        test2(table);
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

}
