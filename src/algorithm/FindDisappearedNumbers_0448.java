package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 */
public class FindDisappearedNumbers_0448 {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int[] a = new int[nums.length+1];
        for (int num : nums) a[num]++;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == 0) list.add(i);
        }
        return list;
    }

    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[(nums[i]-1) % n] += n;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) list.add(i + 1);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers2(new int[]{1,2,3}));
    }
}
