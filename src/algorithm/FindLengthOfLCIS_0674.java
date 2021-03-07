package algorithm;

/**
 * 674. 最长连续递增序列
 */
public class FindLengthOfLCIS_0674 {

    public static int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0) return 0;
        int max = 1;
        for (int i = 1,t = 1; i <= nums.length; i++) {
            if (i < nums.length && nums[i] > nums[i-1]) {
                t++;
            } else {
                max = Math.max(max, t);
                t = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        System.out.println(findLengthOfLCIS(new int[]{1, 3, 5, 7}));
        System.out.println(findLengthOfLCIS(new int[]{1}));

    }
}
