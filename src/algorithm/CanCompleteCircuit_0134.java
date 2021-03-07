package algorithm;

import java.util.Arrays;

/**
 * 加油站
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 */
public class CanCompleteCircuit_0134 {
    /**
     * 分两部分，先重index开始循环到最后，再重0到index，有点弟弟
     * 执行用时：28 ms, 在所有 Java 提交中击败了31.54%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了72.10%的用户
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (Arrays.stream(gas).sum()-Arrays.stream(cost).sum() < 0) return -1;
        for (int start=0,sum=0; start<gas.length; start++,sum=0) {
            for (int i=start; i<gas.length; i++) {
                sum += gas[i] - cost[i];
                if (sum < 0) break;
            }
            if (sum < 0) continue;
            for (int j=0; j<start; j++) {
                sum += gas[j] - cost[j];
                if (sum < 0) break;
            }
            if (sum < 0) continue;
            return start;
        }
        return -1;
    }

    /**
     * 大伙思路，主要是利用了gas总和>cost总和是充分条件，上面也考虑到了，但是没有利用好
     * 一次循环，牛逼
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit2(int[] gas, int[] cost) {
        int rest = 0, run = 0, start = 0;
        for (int i = 0; i < gas.length; ++i){
            run += (gas[i] - cost[i]);
            rest += (gas[i] - cost[i]);
            if (run < 0){
                start = i + 1;
                run = 0;
            }
        }
        return rest < 0 ? -1: start;
    }

    //1,2,3,4,5,6； 1,1,1,1,20,1
    public static void main(String[] args) {
        System.out.println(canCompleteCircuit2(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }
}
