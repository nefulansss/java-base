package com.zhahzhalan.thread;

import sun.security.krb5.internal.TicketFlags;

/**
 * 车站买票 三个窗口买1000张票
 * @Author: zhanglan61
 * @Date: 2020/5/24 14:34
 * @Version: 1.0
 */
public class Ticket {

    public static void main(String[] args) {
        TicketOffice ticketOffice = new TicketOffice(1000);
        new Thread(ticketOffice, "thread-1").start();
        new Thread(ticketOffice, "thread-2").start();
        new Thread(ticketOffice, "thread-3").start();
}

}


class TicketOffice implements Runnable{

    private volatile Integer ticket ;

    public TicketOffice(int ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (ticket>0){
            synchronized (ticket){
                if (ticket<=0){
                    System.out.println("没票了");
                }
                else {
                    System.out.println(Thread.currentThread().getName()+"卖出一张票，还有 "+--ticket+"张");
                }
            }
        }

    }
}