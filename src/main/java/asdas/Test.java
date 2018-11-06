package asdas;

public class Test {

//    public static void main(String[] args) {
//        int c = 0;
//        int b = 1;
//    }

    public static int add(int a , int b){
        int c = 0;
        c = a + b;
        return c;
    }


    /**
     * 0: iconst_0        //常量0压入操作数栈
     * 1: istore_2        //弹出操作数栈栈顶元素，保存到局部变量表第2个位置
     * 2: iload_0         //第0个变量压入操作数栈
     * 3: iload_1         //第1个变量压入操作数栈
     * 4: iadd            //操作数栈中的前两个int相加，并将结果压入操作数栈顶
     * 5: istore_2        //弹出操作数栈栈顶元素，保存到局部变量表第2个位置
     * 6: iload_2         //加载局部变量表的第2个变量到操作数栈顶
     * 7: ireturn         //返回
     */
}
