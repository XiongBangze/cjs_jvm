package lambda;

public class WorkTest {

    public static void main(String[] args) {
        // 通过匿名内部类调用
        WorkerInterface work = new WorkerInterface() {
            @Override
            public int doWork() {
                return 1+1;
            }
        };
        work.doWork();

        // 通过 Lambda 表达式调用
        // Lambda 表达式实际上是一个对象。
        // 我们可以将 Lambda 表达式赋值给一个变量，就可像其它对象一样调用。
        work = ()-> 1+1;
        System.out.println(work);
        work.doWork();
    }
}
