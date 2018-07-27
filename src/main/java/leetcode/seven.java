package leetcode;

public class seven {

    public static int reverse(int x) {
        int result = 1;
        if (x<0){
            result = -1;
            x = x * result;
        }
        long r = 0;
        while (x>0){
            r = r * 10 + x % 10;
            x /= 10;
        }
        r = result * r;
        if (r > Integer.MAX_VALUE || r < Integer.MIN_VALUE) return 0;
    return (int)r;
    }

    public static void main(String[] args) {
        System.out.print(reverse(5240));
    }

}
