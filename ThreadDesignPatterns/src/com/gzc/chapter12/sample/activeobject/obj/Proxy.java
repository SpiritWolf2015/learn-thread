package com.gzc.chapter12.sample.activeobject.obj;

import com.gzc.chapter12.sample.activeobject.*;
import com.gzc.chapter12.sample.activeobject.request.DisplayStringRequest;
import com.gzc.chapter12.sample.activeobject.request.MakeStringRequest;
import com.gzc.chapter12.sample.activeobject.result.FutureResult;
import com.gzc.chapter12.sample.activeobject.result.Result;

class Proxy implements ActiveObject {
    private final SchedulerThread scheduler;
    private final Servant servant;

    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }

    @Override
    public Result<String> makeString(int count, char fillchar) {
        FutureResult<String> future = new FutureResult<>();
        scheduler.invoke(new MakeStringRequest(servant, future, count, fillchar));
        return future;
    }

    @Override
    public void displayString(String string) {
        scheduler.invoke(new DisplayStringRequest(servant, string));
    }
}
