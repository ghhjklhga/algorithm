package algorithm;

import java.util.HashMap;

public class SumOfTwoNum_0001 {
    public static int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];
        HashMap<Integer,Integer> hash = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            hash.put(target-nums[i],i);
        }
        return indexs;
    }


    public static void main(String[] args) {
        int[] a = twoSum(new int[]{0, 3,2,3,100}, 6);
        for (int s: a) {
            System.out.println(s);
        }
    }
}
