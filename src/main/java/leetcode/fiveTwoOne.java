package leetcode;

public class fiveTwoOne {

    public static int findLUSlength(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        if(lenA != lenB)
            return Math.max(lenA,lenB);
        else{
            if(a.equals(b))
                return -1;
            else
                return lenA;
        }
    }

    public static void main(String[] args) {
        System.out.print(findLUSlength("abc","dd"));
    }
}
