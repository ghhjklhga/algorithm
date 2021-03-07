package algorithm;

import java.util.Arrays;

public class SearchRange_0034 {
    /**
     * 暴力
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int first = -1, last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) first = i;
                last = i;
            } else if (nums[i] != target && last != -1)
                break;
        }
        return new int[]{first, last};
    }

    /**
     * 二分，O(nlogn)
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange3(int[] nums, int target) {
        return new int[]{binarySearch(nums, target, true), binarySearch(nums, target, false)};
    }

    private static int binarySearch(int[] nums, int target, boolean leftRight) {
        int left = 0, right = nums.length-1, result = -1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > target) right = mid - 1;
            else if (nums[mid] < target) left = mid + 1;
            else {
                result = mid;
                if (leftRight) right = mid - 1;
                else left = mid + 1;
            }
        }
        return result;
    }
    //leftOrRight为true找左边界 false找右边界


    public static void main(String[] args) {
        Arrays.stream(searchRange3(new int[]{3,3,3,4,5,5,5,6}, 5)).forEach(System.out::println);
    }
}
