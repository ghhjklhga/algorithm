package algorithm;

import java.util.Arrays;

/**
 * 缺失的第一个正数
 *
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 */
public class FirstMissingPositive_0041 {
    /**
     * 直接Arrays。sort，不讲武德，耗子尾汁。
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int i=1;
        Arrays.sort(nums);
        for (int num : nums) if (num>0&&num==i) i++;
        return i;
    }

    /**
     * 先把非正数和大于数组长度的数赋值为数组长度+1，因为如果全部没缺失的话所取得就是数组长度+1的数，例如nums[]{3,6,-1,2}中，-1和6都会被修改为5
     * 然后把 正数并且在数组长度范围内的数，作为下标，修改该下标所在的数据未负数（用来标记）例如如果nums[1]没被修改，就是说nums中没有1。
     * 最后只要判断哪个没被修是大于0的即可（因为要么没被修改而大于零，要么被修改为5（上例子中，也就是长度+1）而大于零）。
     * @param nums
     * @return
     */
    public static int firstMissingPositive2(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int n = nums.length;
        // 非正数和大于数组长度的 赋值 长度+1
        for (int i = 0; i < n; i++) if (nums[i] <= 0 || nums[i] > n) nums[i] = n + 1;
        // 只要数字绝对值 小于 长度，则赋值将 负数赋值到原数组；
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            // 在范围内
            if (num <= n ) {
                // 对应的下标数据，改为负数
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        // 找到第一个大于0的就是最小缺失正整数
        for (int i = 0; i < n; i++) if (nums[i] > 0)return i + 1;
        // 否则就是数组长度+1
        return n + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive2(new int[]{3,6,-1,2}));
    }
}
