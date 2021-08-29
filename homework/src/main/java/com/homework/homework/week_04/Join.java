package com.homework.homework.week_04;

public class Join {

    public static void main(String[] args) {

        Child1 thread1 = new Child1();
        thread1.run();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Child1 extends Thread {


    @Override
    public void run() {
        long start=System.currentTimeMillis();
        int result = sum();
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    }
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
