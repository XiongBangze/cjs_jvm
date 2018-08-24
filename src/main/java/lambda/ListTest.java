package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ListTest {

    public static void main(String[] args) {
        // old way
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//        for (Integer n : list) {
//            System.out.println(n);
//        }

        // 使用 -> 的 Lambda 表达式
        list.forEach(n -> System.out.println(n));

        // 使用 :: 的 Lambda 表达式
        list.forEach(System.out::println);

        evaluate(list, (n) -> true);
    }


    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.print(n + " ");
            }
        }
        System.out.println();
    }
}
