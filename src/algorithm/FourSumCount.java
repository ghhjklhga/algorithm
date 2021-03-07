package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2(28) 到 2(28) - 1 之间，最终结果不会超过 2(31) - 1 。
 *
 */
public class FourSumCount {

    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>(A.length * B.length);
        for (int a : A) {
            for (int b : B) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }
        int count = 0;
        for (int c : C) {
            for (int d : D) {
                count += map.getOrDefault(-(c + d), 0);
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
