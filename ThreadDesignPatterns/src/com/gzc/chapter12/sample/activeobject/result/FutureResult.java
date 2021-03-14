package com.gzc.chapter12.sample.activeobject.result;

public class FutureResult<T> implements Result<T> {
    private Result<T> result;
    private boolean ready = false;

    public synchronized void setResult(Result<T> result) {
        this.result = result;
        this.ready = true;
        notifyAll();
    }

    @Override
    public synchronized T getResultValue() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                // NONE OP
            }
        }
        return result.getResultValue();
    }
}
