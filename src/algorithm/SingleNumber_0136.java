package algorithm;

/**
 * 136. 只出现一次的数字
 */
public class SingleNumber_0136 {

    public int singleNumber(int[] nums) {
        if (nums.length == 0) return 0;
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res^nums[i];
        }
        return res;
    }
}
