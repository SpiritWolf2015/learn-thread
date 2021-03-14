package com.gzc.chapter12.sample.activeobject.request;

import com.gzc.chapter12.sample.activeobject.result.FutureResult;
import com.gzc.chapter12.sample.activeobject.obj.Servant;

public abstract class MethodRequest<T> {
    protected final Servant servant;
    protected final FutureResult<T> future;

    protected MethodRequest(Servant servant, FutureResult<T> future) {
        this.servant = servant;
        this.future = future;
    }

    public abstract void execute();
}
