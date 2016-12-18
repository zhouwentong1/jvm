package com.puhui.demo;

/**
 * Created by zhouwentong on 2016/12/18.
 * 对象自我拯救的演示
 * 1. 对象可以在被 gc 的时候自我拯救
 * 2. 拯救的时机在 finalize 方法被调用的时候，这个方法的优先级比较低，所以要 sleep 一会儿
 * 3. 这个方法只会执行一次，所以拯救的机会只有一次。
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC FINALIZE_ESCAPEGC;

    public void alive() {
        System.out.println("依然存活 :) ");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("执行了 finalize 方法");
        FINALIZE_ESCAPEGC = this;
    }

    public static void main(String[] args) throws InterruptedException {
        FINALIZE_ESCAPEGC = new FinalizeEscapeGC();
        FINALIZE_ESCAPEGC = null;
        System.gc();
        Thread.sleep(500);
        if (FINALIZE_ESCAPEGC != null) {
            FINALIZE_ESCAPEGC.alive();
        } else {
            System.out.println("实例已经被回收");
        }

        FINALIZE_ESCAPEGC = null;
        System.gc();
        Thread.sleep(500);
        if (FINALIZE_ESCAPEGC != null) {
            FINALIZE_ESCAPEGC.alive();
        } else {
            System.out.println("实例已经被回收");
        }
    }
}
