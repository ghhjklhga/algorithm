package algorithm;

import java.util.*;

/**
 * 217. 存在重复元素
 *
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0)+1);
        return map.keySet().size() < nums.length;
//        for (Integer integer : map.values()) if (integer > 1) return true;
//        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate3(int[] nums) {
        return Arrays.stream(nums).distinct().count() < nums.length;
    }

    public boolean containsDuplicate4(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        return set.size() < nums.length;
    }
}
