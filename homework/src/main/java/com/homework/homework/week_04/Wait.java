package com.homework.homework.week_04;

public class Wait {

    public static void main(String[] args) {

//        Child thread1 = new Child();
//        synchronized (this){
//            try {
//                thread1.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                thread1.notify();
//            }
//        }
    }
}

class Child extends Thread {


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
