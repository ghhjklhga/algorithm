package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 */
public class LongestSubarray_1438 {

    public int longestSubarray(int[] nums, int limit) {
        int maxnum = Integer.MIN_VALUE, minnum = Integer.MAX_VALUE;
        int left = 0, right = 0;
        while (right < nums.length) {
            // 新加入窗口的右指针，要重新判断最小最大值
            if (nums[right] > maxnum) maxnum = nums[right];
            if (nums[right] < minnum) minnum = nums[right];
            if (maxnum - minnum > limit) {
                left++;
                // 重新找最小最大值
                maxnum = nums[left];
                minnum = nums[left];
                for (int i = left + 1; i <= right; i++) {
                    if (nums[i] > maxnum) maxnum = nums[i];
                    if (nums[i] < minnum) minnum = nums[i];
                }
            }
            right++;
        }
        // 窗口范围就是答案
        return right - left;
    }

    public int longestSubarray2(int[] nums, int limit) {
        // 维护两个优先队列，最小最大值为队列第一个
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < nums.length && left < nums.length) {
            minQueue.add(nums[right]);
            maxQueue.add(nums[right]);
            // 如果窗口每超出limit，右指针移动，
            if (maxQueue.peek() - minQueue.peek() <= limit) {
                ans = Math.max(ans, right - left + 1);
                right++;
                continue;
            }
            // 两个队列去除第一个指针的数
            maxQueue.remove(nums[left]);
            minQueue.remove(nums[left]);
            left++;
            right++;
        }
        return ans;
    }
}
