package algorithm;

public class NoRepeatWord_1578 {
    /*
    贪心
     */
    public static int minCost(String s, int[] cost) {
        int sum = 0;
        for (int i=0,index=i; i<s.length()-1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {      // 相同
                sum += Math.min(cost[index], cost[i+1]);    // 取最小值
                index = cost[index] > cost[i+1] ? index : i+1;  // 记录下较大值的下标
            } else                                     // 不相同
                index = i+1;                                // 下标重下一个开始
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(minCost("aaabbbabbbb", new int[]{3,5,10,7,5,3,5,5,4,8,1}));
    }
    /**
     *执行用时：12 ms, 在所有 Java 提交中击败了36.89%的用户
     *内存消耗：56.5 MB, 在所有 Java 提交中击败了7.66%的用户
     *好惨
     **/
}
