package com.gzc.appendix_b.double_checked_locking;
import java.util.Date;

// ×无法确保能够正确地运行
public class MySystem {
    /*
    这里在声明变量时使用了volatile关键字来保证其线程间的可见性；在同步代码块中使用二次检查，以保证其不被重复实例化。集合其二者，这种实现方式既保证了其高效性，也保证了其线程安全性。"
    二次检查这段代码, 为什么要二次检查? volatile在此的作用仅是保证可见性? 我想纠正一下,
    内层的检查保证对象在并发时不会重复创建, 外层检查避免每一次获取对象都对资源进行加锁, 影响性能,所以才会有了并发情况下的线程安全的懒汉单例, 即Double Check Lock;
    而volatile在此是禁止指令重排的作用, 保证先初始化完成后, 再把对象引用赋值给instance变量。防止代码被重排序成先把对象引用赋值给instance变量，再完成对象初始化。
    * */
    private /*volatile*/ static MySystem instance = null; // 没有加volatile关键字会有问题
    private Date date = new Date();

    private MySystem() {
    }
    public static MySystem getInstance() {
        if (instance == null) {                 // (a) 第一次test
            synchronized (MySystem.class) {     // (b) 进入synchronized代码块
                if (instance == null) {         // (c) 第二次test
                    instance = new MySystem();  // (d) set
                }
            }                                   // (e) 从synchronized代码块中退出
        }
        return instance;                        // (f)
    }

    public Date getDate() {
        return date;
    }
}
