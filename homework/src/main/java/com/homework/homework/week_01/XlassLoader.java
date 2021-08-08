package com.homework.homework.week_01;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class XlassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new XlassLoader();
        Class<?> cl = classLoader.loadClass("Hello");
        // 输出内容
        for (Method met : cl.getDeclaredMethods()) {
            System.out.println(cl.getSimpleName() + "." + met.getName());
        }
        String methodName = "hello";
        Object instance = cl.getDeclaredConstructor().newInstance();
        Method method = cl.getMethod(methodName);
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String path) throws ClassNotFoundException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path +".xlass");
        try {
            // 读流
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            // 转换
            byte[] classBytes = change(byteArray);
            // 定义类
            return defineClass(path, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(path, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 转换
    private static byte[] change(byte[] array) {
        byte[] target = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            target[i] = (byte) (255 - array[i]);
        }
        return target;
    }
}
