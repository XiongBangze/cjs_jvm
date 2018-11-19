public class IntegerLock {
    static Integer i = 0;

    public static class AddThread extends Thread {
        public void run(){
            for (int j = 0; j <100000 ; j++) {
                synchronized (i){
                    i++;
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        AddThread addThread1 = new AddThread();
        addThread.start();addThread1.start();
        addThread.join();addThread1.join();
        System.out.println(i);
    }
}
