package algorithm;

public class MaxProfit_0188 {

    /**
     * k
     * 0 表示不操作
     * 1 第一次买入
     * 2 第一次卖出
     * 3 第一次买入
     * 4 第一次卖出
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit_10(int k,int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2*k+1];
        // 初始化持有
        for (int j = 1; j < 2 * k; j += 2) {
            dp[0][j] = -prices[0];
        }
        for (int i = 1;i < prices.length; i++) {
            for (int j = 0; j < 2 * k - 1; j += 2) {
                // 当天持有 = max{前一天持有, 前一天0操作->买入}
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                // 当天不持有 = max{前一天不持有, 前一天持有->卖出}
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[prices.length - 1][2 * k];
    }

    public static void main(String[] args) {
        System.out.println(maxProfit_10(2, new int[]{3,2,6,5,0,3}));
    }
}
