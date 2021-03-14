package com.gzc.chapter12.sample.activeobject.request;

import com.gzc.chapter12.sample.activeobject.result.FutureResult;
import com.gzc.chapter12.sample.activeobject.result.Result;
import com.gzc.chapter12.sample.activeobject.obj.Servant;

public class MakeStringRequest extends MethodRequest<String> {
    private final int count;
    private final char fillchar;

    public MakeStringRequest(Servant servant, FutureResult<String> future, int count, char fillchar) {
        super(servant, future);
        this.count = count;
        this.fillchar = fillchar;
    }

    @Override
    public void execute() {
        Result<String> result = servant.makeString(count, fillchar);
        future.setResult(result);
    }
}
