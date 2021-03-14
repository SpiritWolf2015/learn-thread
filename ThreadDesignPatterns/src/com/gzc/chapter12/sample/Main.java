package com.gzc.chapter12.sample;

import com.gzc.chapter12.sample.activeobject.obj.ActiveObject;
import com.gzc.chapter12.sample.activeobject.obj.ActiveObjectFactory;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bobby", activeObject).start();

        new DisplayClientThread("Chris", activeObject).start();
    }
}
