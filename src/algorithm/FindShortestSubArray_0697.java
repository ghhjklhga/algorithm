package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 697. 数组的度
 */
public class FindShortestSubArray_0697 {

    public static int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        // 记录每个数字的出现次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int maxCount = 0;
        // 记录最多的次数的数字，注意，可能会有多个
        List<Integer> list = new ArrayList<>();
        for (Integer key : map.keySet()) {
            if (map.get(key) >= maxCount) {
                if (map.get(key) > maxCount) list.clear();
                list.add(key);
                maxCount = map.get(key);
            }
        }
        if (maxCount == 1) return 1;
        // 遍历次数最多的每个数字的间距，取最小值
        int len = Integer.MAX_VALUE;
        for (Integer num : list) {
            int left = 0, right = nums.length - 1;
            while (left < nums.length) {
                if (nums[left] == num) break;
                left++;
            }
            while (right >= 0) {
                if (nums[right] == num) break;
                right--;
            }
            if (right - left + 1 < len) len = right - left + 1;
        }
        return len;
    }

    public int findShortestSubArray2(int[] nums) {
        int len = nums.length, maxCount = 0, minWindow = 0;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            // 保存每个数字的 [第一次出现]下标和出现次数
            int[] pair = map.get(nums[i]);
            if (pair == null) {
                pair = new int[]{i, 1};
                map.put(nums[i], pair);
            } else {
                pair[1]++;
            }
            if (pair[1] > maxCount) {               // 如果出现次数大于当前，刷新度
                maxCount = pair[1];
                minWindow = i - pair[0] + 1;
            } else if (pair[1] == maxCount) {       // 如果出现次数相等，取最小值
                minWindow = Math.min(minWindow, i - pair[0] + 1);
            }
        }
        return minWindow;
    }

    public static void main(String[] args) {
        System.out.println(findShortestSubArray(new int[]{1,3,2,2,3,1}));
    }
}
