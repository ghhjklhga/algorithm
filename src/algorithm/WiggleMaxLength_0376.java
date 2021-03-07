package algorithm;

/**
 * 摆动序列
 */
public class WiggleMaxLength_0376 {
    /**
     * 正1，负-1
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了44.91%的用户
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        int count = 1, index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i-1] < 0) {
                if (index == 0 || index == 1) {
                    index = -1;
                    count++;
                }
            } else if (nums[i] - nums[i-1] > 0){
                if (index == 0 || index == -1) {
                    index = 1;
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 假设 up[i] 表示 nums[0:i] 中最后两个数字递增的最长摆动序列长度，down[i] 表示 nums[0:i] 中最后两个数字递减的最长摆动序列长度，只有一个数字时默认为 1。
     *
     * 接下来我们进行分类讨论：
     *
     * nums[i+1] > nums[i]
     * 假设 down[i] 表示的最长摆动序列的最远末尾元素下标正好为 i，遇到新的上升元素后，up[i+1] = down[i] + 1 ，这是因为 up 一定从 down 中产生（初始除外），并且 down[i] 此时最大。
     * 假设 down[i] 表示的最长摆动序列的最远末尾元素下标小于 i，设为 j，那么 nums[j:i] 一定是递增的，因为若完全递减，最远元素下标等于 i，若波动，那么 down[i] > down[j]。由于 nums[j:i] 递增，down[j:i] 一直等于 down[j] ，依然满足 up[i+1] = down[i] + 1 。
     * nums[i+1] < nums[i]，类似第一种情况
     * nums[i+1] == nums[i]，新的元素不能用于任何序列，保持不变
     *
     * 这题其实只需要求振动的次数（也就是一个递增或递减子序列只需要从中取出一个值即可，我们只关注值的个数，而不必关心这个值是中间还是结尾），
     * 所以只需要知道上次产生振动之后序列是上升还是下降，后续只要相反就说明产生振动了。
     */
    public static int wiggleMaxLength2(int[] nums) {
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            // up一定是上一次dmwn的结果
            if (nums[i] > nums[i - 1]) up = down + 1;
            // 反之亦然
            if (nums[i] < nums[i - 1]) down = up + 1;
        }
        return nums.length == 0 ? 0 : Math.max(down, up);
    }

    public static void main(String[] args) {
//        System.out.println(wiggleMaxLength(new int[]{0, 0}));
        System.out.println(wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8}));
    }
}
