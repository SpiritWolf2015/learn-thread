package com.gzc.chapter9.jucSample;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;

public class FutureData extends FutureTask<RealData> implements Data {

    public FutureData(Callable<RealData> callable) {
        super(callable);
    }

    @Override
    public String getContent() {
        String string = null;
        try {
            // 调用get()方法获取结果
            RealData realData = get();
            string = realData.getContent();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return string;
    }
}
