package thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThresd2 {

    private static Lock lock = new ReentrantLock();

    private static int state = 0;

    private static  int RUN_TIME = 100;


    Runnable runnableA = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < RUN_TIME;) {
                lock.lock();
                if (state%3 == 0){
                    System.out.println("第"+(i+1)+"次:");
                    System.out.println("A");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    };

    Runnable runnableB = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < RUN_TIME;) {
                lock.lock();
                if (state%3 == 1){
                    System.out.println("第"+(i+1)+"次:");
                    System.out.println("B");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    };

    Runnable runnableC = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < RUN_TIME;) {
                lock.lock();
                if (state%3 == 2){
                    System.out.println("第"+(i+1)+"次:");
                    System.out.println("C");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    };

    public static void main(String[] args) {

        TestThresd2 testThresd2 = new TestThresd2();
        Thread threadA = new Thread(testThresd2.runnableA);
        Thread threadB = new Thread(testThresd2.runnableB);
        Thread threadC = new Thread(testThresd2.runnableC);

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
