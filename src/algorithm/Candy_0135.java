package algorithm;

import java.util.Arrays;

/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 */
public class Candy_0135 {

    public int candy(int[] ratings) {
        // 左规则和右规则
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        // 先填充1，最少分一颗
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        // 左规则
        for(int i = 1; i < ratings.length; i++)
            if(ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
        int count = left[ratings.length - 1];
        // 右规则
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1;
            // 每个位置取最大值
            count += Math.max(left[i], right[i]);
        }
        return count;
    }

    public int candy2(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        // 左规则
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
            else left[i] = 1; // 默认1，最少分一颗
        }
        int right = 0, ret = 0;
        // 右规则
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) right++;
            else right = 1; // 默认1，最少分一颗
            // 每个位置取最大值
            ret += Math.max(left[i], right);
        }
        return ret;
    }
}
