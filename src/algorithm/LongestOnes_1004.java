package algorithm;

/**
 * 1004. 最大连续1的个数 III
 */
public class LongestOnes_1004 {

    public int longestOnes(int[] A, int K) {
        // 补0次数
        int zeroCount = 0;
        int left = 0, right = 0;
        while (right < A.length) {
            if (A[right] == 0) zeroCount++;
            // 如果 补0次数 大于 K，左指针移动
            if (zeroCount > K) {
                // 如果左指针指向0，则移动前减回0
                if (A[left] == 0) zeroCount--;
                left++;
            }
            // 右指针移动
            right++;
        }
        // 窗口值则是最长1的个数
        return right - left;
    }
}
