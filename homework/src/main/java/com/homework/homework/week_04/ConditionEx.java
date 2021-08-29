package com.homework.homework.week_04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class ConditionEx {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        lockTh2 th=new lockTh2();
        th.setCondition(condition);
        try {
            condition.await();
            th.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class lockTh2 extends Thread {

    private Condition condition;

    @Override
    public void run() {
        long start=System.currentTimeMillis();
        int result = sum();
        condition.signal();
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    }
    private  int sum() {

        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;

        return fibo(a-1) + fibo(a-2);
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
