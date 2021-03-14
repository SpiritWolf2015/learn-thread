package com.gzc.chapter12.sample.activeobject.result;

public class RealResult<T> implements Result<T> {
    private final T resultValue;

    public RealResult(T resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public T getResultValue() {
        return resultValue;
    }
}
