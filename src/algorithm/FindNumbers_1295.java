package algorithm;

/**
 * 1295. 统计位数为偶数的数字
 */
public class FindNumbers_1295 {

    /**
     * 比字符串快
     * @param nums
     * @return
     */
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (check(num)) {
                count++;
            }
        }
        return count;
    }

    private boolean check(int num) {
        int t = 1;
        while (num / 10 > 0) {
            t++;
            num /= 10;
        }
        return t % 2 == 0;
    }
}
