package com.gzc.chapter4.sample;

import java.io.IOException;
import java.util.Random;

public class ChangerThread extends Thread {
    private final Data data;
    private final Random random = new Random();

    public ChangerThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                data.change("No." + i);             // 修改数据
                Thread.sleep(random.nextInt(1000)); // 执行其他操作
                data.save();                        // 显式地保存
                i++;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
