package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadAddInt {

    private static int ticket = 100000;

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static void delTicket(){
        try {
            reentrantLock.lock();
            ticket--;
//            System.out.println(Thread.currentThread().getName()  + "工作中!!!!"+ticket);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    private static int count =0;

    private static  synchronized void inc(){
        count++;
    }

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static void addAtomicInteger(){
        atomicInteger.addAndGet(1);
    }

    private static volatile int volatileInt = 0;

    private static void addVolatileInt(){
        volatileInt++;
    }

    public static void main(String[] args) throws InterruptedException {
//        Runnable runnable = () -> inc();
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(runnable);
//            thread.start();
//        }
//        Thread.sleep(1000);
//        System.out.println(count);
//
//
//        Runnable runnable1 = () -> addAtomicInteger();
//        ExecutorService executorService1 = Executors.newFixedThreadPool(11);
//        for (int i = 0; i < 11; i++) {
//            Thread thread = new Thread(runnable1);
//            executorService1.execute(thread);
//        }
//        executorService1.shutdown();
//        Thread.sleep(1000);
//        System.out.println(atomicInteger);
//
//        Runnable runnable2 = () -> addVolatileInt();
//        ExecutorService executorService2 = Executors.newFixedThreadPool(11);
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(runnable2);
//            executorService2.execute(thread);
//        }
//        executorService2.shutdown();
//        Thread.sleep(1000);
//        System.out.println(volatileInt);


        Runnable runnable3 = () -> delTicket();
        long l = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis()+"====");
        for (int i = 0; i <10000 ; i++) {
            Thread thread = new Thread(runnable3);
            thread.start();
            System.out.println(System.currentTimeMillis()+"===="+ticket);
        }
        System.out.println(System.currentTimeMillis()+"===="+ticket);
        Thread.sleep(200000);
        System.out.println(ticket);
    }

}
