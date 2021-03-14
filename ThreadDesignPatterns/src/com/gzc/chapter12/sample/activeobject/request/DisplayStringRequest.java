package com.gzc.chapter12.sample.activeobject.request;

import com.gzc.chapter12.sample.activeobject.obj.Servant;
import com.gzc.chapter12.sample.activeobject.request.MethodRequest;

public class DisplayStringRequest extends MethodRequest<Object> {
    private final String string;

    public DisplayStringRequest(Servant servant, String string) {
        super(servant, null);
        this.string = string;
    }

    @Override
    public void execute() {
        servant.displayString(string);
    }
}
