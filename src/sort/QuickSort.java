package sort;

import java.util.Arrays;
import java.util.stream.Stream;

public class QuickSort {

    public static int[] sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int t = nums[left];
        int start = left;
        int end = right;
        while (start < end) {
            // 右边
            while (start < end && nums[end] >= t) end--;
            if (start < end) nums[start++] = nums[end];
            // 左边
            while (start < end && nums[start] < t) start++;
            if (start < end) nums[end--] = nums[start];
        }
        // 退出时 start == end，t填坑
        nums[start] = t;
        // 递归两边
        quickSort(nums, left, start);
        quickSort(nums, start + 1, right);
    }

    public static void main(String[] args) {
        Arrays.stream(sort(new int[]{2,4,3,6,1,0,9,7,8,5})).forEach(System.out::print);
    }
}
