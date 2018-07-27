package leetcode;

import java.util.HashMap;

public class one {

    public static int[] twoSum(int[] nums, int target) {
        int [] res = new int[2];
        if(nums==null||nums.length<2)
            return res;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(target-nums[i])){
                map.put(nums[i],i);
            }else{
                res[0]= map.get(target-nums[i]);
                res[1]= i;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums ={2,7,11,15};
        int target = 9;
        twoSum(nums ,target);
    }
}
