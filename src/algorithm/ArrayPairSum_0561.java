package algorithm;

import java.util.Arrays;

/**
 * 561. 数组拆分 I
 */
public class ArrayPairSum_0561 {

    public int arrayPairSum(int[] nums) {
        if (nums.length == 2) return Math.min(nums[0], nums[1]);
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }
}
