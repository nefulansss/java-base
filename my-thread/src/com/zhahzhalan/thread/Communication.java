package com.zhahzhalan.thread;

/**
 * 线程间通信    创建两个线程，其中一个输出1-52，另外一个输出A-Z。输出格式要求：12A 34B 56C 78D ...
 * @Author: zhanglan61
 * @Date: 2020/5/24 15:03
 * @Version: 1.0
 */
public class Communication {
    public static void main(String[] args) {
        Object object = new Object();
        new Thread(new Number(object)).start();
        new Thread(new Character(object)).start();
    }
}

class Number implements Runnable
{
    private Object object;

    public Number(Object object)
    {
        this.object = object;
    }

    @Override
    public void run()
    {
        synchronized (object)
        {
            for (int i = 1; i < 53; i++)
            {
                if (i > 1 && i % 2 == 1)
                {
                    System.out.print(" ");
                }
                System.out.print(i);
                if (i % 2 == 0)
                {
                    object.notifyAll();
                    try
                    {
                        object.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Character implements Runnable
{
    private Object object;

    public Character(Object object)
    {
        this.object = object;
    }

    @Override
    public void run()
    {
        synchronized (object)
        {
            for (char i = 'A'; i <= 'Z'; i++)
            {
                System.out.print(i);
                object.notifyAll();
                if (i < 'Z')
                {
                    try
                    {
                        object.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}