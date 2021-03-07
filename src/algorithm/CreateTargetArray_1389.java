package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1389. 按既定顺序创建目标数组
 */
public class CreateTargetArray_1389 {
    /**
     * @param nums
     * @param index
     * @return
     */
    public static int[] createTargetArray(int[] nums, int[] index) {
        int len = nums.length;
        int[] ans = new int[len];
        // 遍历下标数组
        for (int i = 0; i < len; i++)
            // 遍历小标数组 < 当前下标 部分
            for (int j = 0; j < i; j++)
                // 如果大于等于当前下标就向前移一位
                index[j] += index[j] >= index[i] ? 1 : 0;
        for (int i = 0; i < len; i++) ans[index[i]] = nums[i];
        return ans;
    }

    /**
     * 不讲武德做法
     * @param nums
     * @param index
     * @return
     */
    public static int[] createTargetArray2(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>((int)(nums.length * 1.5));
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Arrays.stream(createTargetArray(new int[]{4,2,4,3,2}, new int[]{0,0,1,3,1})).forEach(System.out::println);
    }
}
