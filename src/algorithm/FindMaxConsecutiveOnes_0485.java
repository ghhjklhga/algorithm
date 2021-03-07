package algorithm;

/**
 * 485. 最大连续1的个数
 */
public class FindMaxConsecutiveOnes_0485 {

    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int t = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (t > maxCount) maxCount = t;
                t = 0;
            } else {
                t++;
            }
        }
        // 最后还要比较一次
        maxCount = Math.max(maxCount, t);
        return maxCount;
    }

    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes2(int[] nums) {
        int fast = 0, slow = 0, maxCount  = 0;
        while (slow < nums.length) {
            if (nums[slow] != 1) slow++;
            else {
                fast = slow;
                while (fast < nums.length && nums[fast] == 1) fast++;
                maxCount  = Math.max(fast - slow, maxCount );
                slow = fast;
            }
        }
        return maxCount ;
    }

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1}));
    }
}
