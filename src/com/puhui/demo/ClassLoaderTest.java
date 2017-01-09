package com.puhui.demo;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wentong on 2017/1/9.
 * 自定义类加载器
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf('.') + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);
                    return defineClass(name, buffer, 0, buffer.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };
        Object target = myClassLoader.loadClass("com.puhui.demo.ClassLoaderTest").newInstance();
        System.out.println(target.getClass());
        System.out.println(target instanceof com.puhui.demo.ClassLoaderTest);

    }
}
