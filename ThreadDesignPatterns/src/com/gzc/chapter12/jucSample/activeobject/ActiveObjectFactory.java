package com.gzc.chapter12.jucSample.activeobject;

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        return new ActiveObjectImpl();
    }
}
