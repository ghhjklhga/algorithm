package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 翻转对
 *
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 */
public class ReversePairs {
    private static int count = 0;
    /**
     * 暴力，超时
     * @param nums
     * @return
     */
    public static int reversePairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long)nums[i] > (long)nums[j]*2) count++;
            }
        }
        return count;
    }


    /**
     * 归并
     * @param nums
     * @return
     */
    public static int reversePairs2(int[] nums) {
        sort(nums, 0, nums.length-1);
        return count;
    }

    private static void sort(int[] nums, int start, int end) {
        if (start >= end) return;
        int middle = (start + end) / 2;
        sort(nums, start, middle);
        sort(nums, middle+1, end);
        merge(nums, start, middle, end);
    }

    private static void merge(int[] nums, int start, int middle, int end) {
        for (int i=middle,j=end; i >= start; i--) {
            // 前半段最大的都比后半段最小*2的小，break
            if ((long)nums[middle+1]*2 >= nums[i]) break;
            while (j > middle && (long)nums[j] * 2 >= (long)nums[i]) j--;
            count += (j - middle);
        }

        // 合并
        int index=0, i=start, j=middle+1;
        int[] t = new int[end-start+1];
        // 现将两边按大小弄到新数组
        while (i<=middle && j<=end) {
            if (nums[i] < nums[j]) t[index++] = nums[i++];
            else if (nums[i] >= nums[j]) t[index++] = nums[j++];
        }
        // 剩下的，无论是哪边有剩下的，都要放进数组，而且未放进数组的，肯定是有序的
        while (i <= middle) t[index++] = nums[i++];
        while (j <= end) t[index++] = nums[j++];
        // 覆盖到到原数组里
        System.arraycopy(t, 0, nums, start, t.length);
    }


    private static void merge2(int[] nums, int lo, int mid, int hi) {
        int[] help = new int[hi+1];
        for(int k = lo; k <= hi; k++) {
            help[k] = nums[k];
        }

        //双指针查找翻转对数量
        int i = mid, j = hi;
        while(i >= lo) {
            if((long)nums[mid + 1]*2>= (long)nums[i]) break;
            while(j > mid && (long)nums[j] * 2 >= (long)nums[i]) j--;
            count += (j - mid);
            i--;
        }

        //合并
        i = lo;
        j = mid + 1;
        int k = lo;
        while(i <= mid || j <= hi) {
            if(j > hi) {
                nums[k++] = help[i++];
            }else if(i > mid) {
                nums[k++] = help[j++];
            }else if(help[i] <= help[j]) {
                nums[k++] = help[i++];
            }else {
                nums[k++] = help[j++];
            }
        }

    }

    /**
     * 二分法
     * @param nums
     * @return
     */
    public static int reversePairs3(int[] nums) {
        List<Long> list = new ArrayList<>();
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans += binSearch(list, (long) nums[i]);
            list.add(binSearch(list, (long) nums[i] * 2), (long) nums[i] * 2);
        }
        return ans;
    }

    private static int binSearch(List<Long> list, long target) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(reversePairs3(new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647}));
    }
}
