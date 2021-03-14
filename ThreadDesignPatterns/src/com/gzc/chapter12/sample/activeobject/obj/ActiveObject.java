package com.gzc.chapter12.sample.activeobject.obj;

import com.gzc.chapter12.sample.activeobject.result.Result;

public interface ActiveObject {
    Result<String> makeString(int count, char fillchar);

    void displayString(String string);
}
