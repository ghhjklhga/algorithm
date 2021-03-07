package algorithm;

/**
 * 665. 非递减数列
 */
public class CheckPossibility_0665 {

    public static boolean checkPossibility(int[] nums) {
        if (nums.length < 3) return true;
        int mark = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                if (mark == 1) return false;
                if (i > 0 && nums[i + 1] < nums[i - 1]) nums[i + 1] = nums[i];
                mark = 1;
            }
        }
        return true;
    }

    public boolean checkPossibility3(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                nums[i] = y;
                if (isSorted(nums)) {
                    return true;
                }
                nums[i] = x; // 复原
                nums[i + 1] = x;
                return isSorted(nums);
            }
        }
        return true;
    }

    public boolean isSorted(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > nums[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkPossibility(new int[]{4, 2, 3}));
    }
}
