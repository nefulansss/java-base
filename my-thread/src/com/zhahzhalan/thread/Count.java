package com.zhahzhalan.thread;

/**
 * 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10,
 * 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20….以此类推,
 * 直到打印到75. 程序的输出结果应该为:
 * @Author: zhanglan61
 * @Date: 2020/5/24 17:59
 * @Version: 1.0
 */
public class Count {

    public static void main(String[] args) {
        Object object =new Object();
        new Thread(new PrintRunnable(object,1)).start();
        new Thread(new PrintRunnable(object,2)).start();
        new Thread(new PrintRunnable(object,3)).start();
    }



}


class PrintRunnable implements Runnable {
    private static volatile int printNum = 0;

    private Object object;

    private int threadId;

    public PrintRunnable(Object object, int threadId) {
        super();
        this.object = object;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while (printNum < 75) {
            synchronized (object) {
                if (printNum / 5 % 3 + 1 == threadId) {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("线程" + threadId + ":" + (++printNum));
                    }
                    object.notifyAll();
                } else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
