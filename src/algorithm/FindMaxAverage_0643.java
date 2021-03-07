package algorithm;

import java.util.Arrays;

/**
 * 643. 子数组最大平均数 I
 */
public class FindMaxAverage_0643 {

    public static double findMaxAverage(int[] nums, int k) {
        double max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i < k) {
                max += nums[i];
                continue;
            }
            sum -= nums[i - k];
            max = Math.max(max, sum);
        }
        return max / k;
    }

    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
    }
}
