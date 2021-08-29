package com.homework.homework.week_04;

import java.util.concurrent.locks.ReentrantLock;

public class Lock {

    static final ReentrantLock lock = new ReentrantLock(true);
    public static void main(String[] args) {
        lockTh th=new lockTh();
        lock.lock();
        th.run();
        lock.unlock();
    }
}

class lockTh extends Thread {


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
