package algorithm;

/**
 * 121. 买卖股票的最佳时机
 */
public class MaxProfit_0121 {

    /**
     * 贪心
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        if (prices.length == 0) return 0;
        int[] max = new int[prices.length];
        for (int i = prices.length-2; i >= 0; i--) {
            max[i] = Integer.max(prices[i+1], max[i+1]);
        }
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            res = Integer.max(res, max[i] - prices[i]);
        }
        return res;
    }

    /**
     * 贪心
     * @param prices
     * @return
     */
    public int maxProfit2(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit1(new int[]{7,1,5,3,6,4}));
    }
}
