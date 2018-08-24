package lock;

import java.util.ArrayList;
import java.util.List;

public class AddThread implements Runnable {


    private ReentrantLockTest task;

    public AddThread(ReentrantLockTest task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.add();
    }
    public static void main(String[] args) {
        ReentrantLockTest task = new ReentrantLockTest();

        Thread t1=new Thread(new AddThread(task));
        Thread t3=new Thread(new AddThread(task));
        Thread t7=new Thread(new AddThread(task));
        Thread t8=new Thread(new AddThread(task));
        Thread t2 = new Thread(new SubThread(task));
        Thread t4 = new Thread(new SubThread(task));
        Thread t5 = new Thread(new SubThread(task));
        Thread t6 = new Thread(new SubThread(task));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();

    }

}
