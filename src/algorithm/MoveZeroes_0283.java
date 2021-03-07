package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
public class MoveZeroes_0283 {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了13.00%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了66.68%的用户
     * @param nums
     */

    public static void moveZeroes(int[] nums) {
        int[] index=new int[nums.length];
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int start=0, end=nums.length-1;
        for (int i=0; i<nums.length; i++) {
            if (start > end) break;
            if (nums[i] == 0) index[end--] = i;
            else index[start++] = i;
        }
        for (int i=0; i<nums.length; i++) nums[i] = list.get(index[i]);
    }

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了9.86%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了86.63%的用户
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {
        List<Integer> list = new ArrayList<>((int)(nums.length * 1.5));
        for (int i=nums.length-1; i>=0; i--) {
            if (nums[i]==0) list.add(list.size(), nums[i]);
            else list.add(0, nums[i]);
        }
        for (int i = 0; i < list.size(); i++) nums[i] = list.get(i);
    }

    /**
     * 除了非零的，其他的肯定为0，所以只要把非0的移到前面就行，后面补0
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了86.63%的用户
     * @param nums
     */
    public static void moveZeroes3(int[] nums) {
        int j = 0;
        for(int i=0; i<nums.length; i++)
            if(nums[i] != 0) nums[j++] = nums[i];
        while(j < nums.length) nums[j++] = 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes3(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }
}
