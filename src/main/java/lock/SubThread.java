package lock;

public class SubThread implements Runnable{
    private ReentrantLockTest task;

    public SubThread(ReentrantLockTest task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.sub();
    }
}
