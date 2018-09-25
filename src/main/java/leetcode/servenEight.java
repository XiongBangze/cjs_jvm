package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 78. 子集
 */
public class servenEight {
    public static List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        int i=0;
        ArrayList<Integer> tmp=new ArrayList<Integer>();
        result.add(tmp);
        find(i,tmp,nums);
        return result;
    }

    /**
     * 原来这道题是不在乎顺序的。。我用的方式是我习惯的。。没亮点，所以用List了。。
     * 用bit位可也咯，用boolean数组代替也好。。。都可以。。看你习惯哪种了，反正没添加一个，都要遍历一次，心累
     *
     * 对了，List是引用。。所以要重新创建一个新的对象才行哦。。。不然就挂了。。
     * */
    static List<List<Integer>> result = new ArrayList<List<Integer>>();
//    int[] nums ={1,2,3};
//    public void find(int index,List<Integer> last){
//        if(index>=nums.length)
//            return ;
//        ArrayList<Integer> item=new ArrayList<Integer>();
//        item.addAll(last);
//        item.add(nums[index]);
//        result.add(item);
//        find(index+1,last);
//        find(index+1,item);
//    }

    public static void find(int index,List<Integer> last,int[] nums){
        if(index>=nums.length)
            return ;
        ArrayList<Integer> item=new ArrayList<Integer>();
        item.addAll(last);
        item.add(nums[index]);
        result.add(item);
        find(index+1,last,nums);
        find(index+1,item,nums);
    }

    public static void main(String[] args) {
        int[] nums ={5,2,3};
        List<List<Integer>> subsets = subsets1(nums);
        subsets.get(0);
    }


    //需要好好理解！！！
    //可以跑一边代码对照输出顺序理解实现过程
    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums.length ==0){
            return list;
        }
        backtrack(list,new ArrayList<Integer>(),nums,0);
        return list;
    }
    private static void backtrack(List<List<Integer>> list,List<Integer> temp,int[] nums,int begin){
        list.add(new ArrayList<Integer>(temp));
        for(int i=begin;i<nums.length;i++){
            temp.add(nums[i]);
            backtrack(list,temp,nums,i+1);
            temp.remove(temp.size()-1);
        }
    }

}
