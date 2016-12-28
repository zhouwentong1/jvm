package com.puhui.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wentong on 2016/12/28.
 */
public class JConsoleDemo1 {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }

    public static void fillHeap( int num) throws Exception {
        List<OOMObject> list =  new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
}
