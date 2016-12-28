package com.puhui.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wentong on 2016/12/28.
 */
public class JConsoleDemo {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        createBusyThread();
        bufferedReader.readLine();
        Object o = new Object();
        createLockThread(o);
    }

    public static void createBusyThread() {
        Thread thread = new Thread(() -> {
            for (;;) {

            }
        },"testBusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"testLockThread");
        thread.start();
    }
}
