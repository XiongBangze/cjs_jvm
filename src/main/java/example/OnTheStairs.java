package example;

public class OnTheStairs {

    public static int all = 0;
    // 某人上楼梯，他一步可以迈一个台阶，两个台阶或三个台阶，共有n个台阶，编程输出他所有可能上法
    /**
     * @param n
     *            n个台阶
     * @param sum
     *            现在在第几个台阶
     * @param i
     *            现在迈i个台阶
     * @param record
     */
    public static void onStairs(int n, int sum, int i, String record) {
        if (sum + i > n) {
            return;
        } else if (sum + i == n) {
            System.out.println("走了" + record.length() + "步，方式是" + record);
            all++;
            return;
        } else {
            onStairs(n, sum + i, 1, record + 1);
            onStairs(n, sum + i, 2, record + 2);
//            onStairs(n, sum + i, 3, record + 3);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println("列举所有上"+n+"台阶的所有方法:");
        onStairs(n, 0, 0, "");
        System.out.println("共"+all+"种方法");

        int steps = Steps(n);
        System.out.print(steps);
    }

    static int Steps(int n)
    {
        if(n==1)
            return 1;
        if(n==2)
            return 2;
        return Steps(n-1)+Steps(n-2);
    }


}
