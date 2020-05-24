package com.zhahzhalan.pool;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @Author: zhanglan61
 * @Date: 2020/5/24 18:14
 * @Version: 1.0
 */
public class MyThreadPool {

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(50);

        ThreadPoolExecutor tickets = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue <Runnable>());

        tickets.execute(()->{
            System.out.println("哈哈哈哈");
        });



    }

}
