package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 分割数组为连续子序列
 *
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度为 3 的子序列，其中每个子序列都由连续整数组成。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 */
public class IsPossible_0659 {

    public boolean isPossible(int[] nums) {
        // 用一个哈希表统计每个数字出现的次数
        Map<Integer, Integer> countNum = new HashMap<>();
        for (int num : nums) countNum.put(num, countNum.getOrDefault(num, 0) + 1);
        // 定义一个哈希表记录最长的子序列
        Map<Integer, Integer> tail = new HashMap<>();
        for (int num : nums) {
            int count = countNum.getOrDefault(num, 0);
            if (count <= 0) {//当前元素已经用完，直接跳过
                continue;
            } else if (tail.getOrDefault(num - 1, 0) > 0) {//前面还有数字，可以构成以num结尾的子序列
                countNum.put(num, count - 1);
                tail.put(num - 1, tail.get(num - 1) - 1);//覆盖当前最长的子序列
                tail.put(num, tail.getOrDefault(num, 0) + 1);//当前以num结尾的子序列+1
            } else if (countNum.getOrDefault(num + 1, 0) > 0 && countNum.getOrDefault(num + 2, 0) > 0) {//前面无数字构成子序列后，判断能不能跟后面的构成子序列
                countNum.put(num, count - 1);
                countNum.put(num + 1, countNum.get(num + 1) - 1);
                countNum.put(num + 2, countNum.get(num + 2) - 1);
                tail.put(num + 2, tail.getOrDefault(num + 2, 0) + 1);//当前以num+2结尾的子序列+1
            } else
                return false;//前后不能构成子序列则不成立
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
