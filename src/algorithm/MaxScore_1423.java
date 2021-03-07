package algorithm;

import java.util.Arrays;

/**
 * 1423. 可获得的最大点数
 */
public class MaxScore_1423 {

    public static int maxScore(int[] cardPoints, int k) {
        if (cardPoints.length == 1) return cardPoints[0];
        int minSum = 0;
        int sum = 0;
        int windowSum = 0;
        // 滑动窗口大小为 n-k
        int windowRize = cardPoints.length - k;
        for (int i = 0; i < cardPoints.length; i++) {
            // 选前 n-k 个作为初始值
            if (i < windowRize) windowSum += cardPoints[i];
            sum += cardPoints[i];
        }
        minSum = windowSum;
        for (int i = 0; i + windowRize < cardPoints.length; i++) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            windowSum = windowSum - cardPoints[i] + cardPoints[windowRize + i];
            minSum = Math.min(minSum, windowSum);
        }
        // 总数 - 窗口最小 = 两头最大
        return sum - minSum;
    }

    public int maxScore2(int[] cardPoints, int k) {
        int n = cardPoints.length;
        // 滑动窗口大小为 n-k
        int windowSize = n - k;
        // 选前 n-k 个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; ++i) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < n; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }

    public static void main(String[] args) {
        System.out.println(maxScore(new int[]{1,2,3,4,5,6,1}, 3));
    }
}
