package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxNumGap {
    /**
     * 桶排序
     * @param nums
     * @return
     */
    public static int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        // 计算最大值最小值
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Integer.min(num, min);
            max = Integer.max(num, max);
        }
        // 创建桶。
        int compare = (max + min) / 5 > 0 ? (max + min) / 5 : 1;
        int bucketNum = 6;
        List<List<Integer>> buckets = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            buckets.add(new ArrayList<>());
        }
        // 将数据放到各桶里
        for (int num : nums) {
            buckets.get((num - min) / compare).add(num);
        }
        // 排序各个桶
        buckets.forEach(a -> a.sort(Integer::compareTo));
        // 将桶中的元素赋值到原序列
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (Integer aBucket : bucket) {
                nums[index++] = aBucket;
            }
        }
        // 提取数据
        max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i-1] > max) max = nums[i] - nums[i-1];
        }
        return max;
    }

    /**
     * 排序法
     * @param nums
     * @return
     */
    public static int maximumGap2(int nums[]) {
        if (nums.length < 2) return 0;
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i-1] > max) max = nums[i] - nums[i-1];
        }
        return max;
    }

    public int maximumGap3(int[] nums) {
        // 小于2直接返回0
        if(nums.length < 2) return 0;
        int n = nums.length;
        // 获取最大最小值
        int max = -1, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        // 最大最小相同直接返回
        if(max - min == 0) return 0;
        // 用于存放每个桶的最大最小值
        int[] bucketMin = new int[n-1];
        int[] bucketMax = new int[n-1];
        Arrays.fill(bucketMax, -1);
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        // 确定桶的间距
        int interval = (int) Math.ceil((double)(max - min) / (n - 1));
        for (int num : nums) {
            // 找到每一个值所对应桶的索引
            int index = (num - min) / interval;
            if (num == min || num == max) continue;
            bucketMax[index] = Math.max(bucketMax[index], num);
            bucketMin[index] = Math.min(bucketMin[index], num);
        }
        // maxGap 表示桶之间最大的差距
        int maxGap = 0;
        // preMax 表示前一个桶的最大值
        int previousMax = min;
        for(int i = 0; i < n - 1; i++) {
            // 表示某一个桶为空
            // 但凡某一个桶不为空，都会在前面的数据中更新掉bucketMax的值
            if(bucketMax[i] == -1)     continue;
            maxGap = Math.max(bucketMin[i] - previousMax, maxGap);
            previousMax = bucketMax[i];
        }
        maxGap = Math.max(maxGap, max - previousMax);
        return maxGap;
    }

    // 线性时间复杂度和空间复杂度 不能用Arrays.sort
    public int maximumGap4(int[] nums) {
        if (nums.length < 2) return 0;
        int len = nums.length;
        // 找出最大值和最小值 为了方便后面确定桶的数量
        int max = -1, min = Integer.MAX_VALUE;
        for (int i  = 0; i < len; i++) {
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }
        // 排除nums全部为一样的数字，nums = [1,1,1,1,1,1];
        if (max - min == 0) return 0;
        // 用于存放每个桶的最大值
        int[] bucketMin = new int[len - 1];
        // 用于存放每个桶的最小值
        int[] bucketMax = new int[len - 1];
        Arrays.fill(bucketMax, -1);
        Arrays.fill(bucketMin, Integer.MAX_VALUE);

        // 确定桶的间距
        int interval = (int)Math.ceil((double)(max - min) / (len - 1));
        for (int i = 0; i < len; i++) {
            // 找到每一个值所对应桶的索引
            int index = (nums[i] - min) / interval;
            if (nums[i] == min || nums[i] == max) continue;
            // 更新每个桶的数据
            bucketMax[index] = Math.max(bucketMax[index], nums[i]);
            bucketMin[index] = Math.min(bucketMin[index], nums[i]);
        }
        // maxGap 表示桶之间最大的差距
        int maxGap = 0;
        // preMax 表示前一个桶的最大值
        int preMax = min;
        for (int i = 0; i < len - 1; i++) {
            // 表示某一个桶为空
            // 但凡某一个桶不为空，都会在前面的数据中更新掉bucketMax的值
            if (bucketMax[i] == -1) continue;
            maxGap = Math.max(bucketMin[i] - preMax, maxGap);
            preMax = bucketMax[i];
        }
        // [1,10000000]
        maxGap = Math.max(maxGap, max - preMax);
        return maxGap;
    }

    public static void main(String[] args) {
        maximumGap2(new int[]{1,1,1,1});
    }
}
