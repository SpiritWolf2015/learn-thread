package com.gzc.chapter12.sample.activeobject.obj;

import com.gzc.chapter12.sample.activeobject.ActivationQueue;
import com.gzc.chapter12.sample.activeobject.SchedulerThread;
import com.gzc.chapter12.sample.activeobject.obj.ActiveObject;
import com.gzc.chapter12.sample.activeobject.obj.Proxy;
import com.gzc.chapter12.sample.activeobject.obj.Servant;

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        ActivationQueue queue = new ActivationQueue();
        SchedulerThread scheduler = new SchedulerThread(queue);

        Servant servant = new Servant();
        Proxy proxy = new Proxy(scheduler, servant);
        scheduler.start();

        return proxy;
    }
}
