package algorithm;

/**
 * 122. 买卖股票的最佳时机 II
 */
public class MaxProfit_0122 {
    /**
     * dp
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Integer.max(dp[i-1][0], prices[i] + dp[i-1][1]);
            dp[i][1] = Integer.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[prices.length-1][0];
    }

    /**
     * 算法题（×） 脑筋急转弯题( √ ）
     *
     * 扫描一遍 只要后一天比前一天大 就把这两天的差值加一下
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            sum+= prices[i]>prices[i-1] ? prices[i]-prices[i-1] : 0;
        }
        return sum;
    }
}
