package algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 1319. 连通网络的操作次数
 */
public class MakeConnected_1319 {

    public int makeConnected(int n, int[][] connections) {
        int[] pre = new int[n];     // 集合顶点（祖先）
        int remain = 0; // 剩余网线
        int setCount = 0;  // 集合数量
        // 初始化，自己是自己的祖先
        for (int i = 0; i < pre.length; i++) pre[i] = i;
        // 遍历所有的网线连通情况，如果连通则不再合并 剩余网线+1，否则合并。
        for (int[] connection : connections) {
            int x = connection[0];
            int y = connection[1];
            if (find(pre, x) == find(pre, y)) {         // 如果连通，剩余网线+1
                remain++;
            } else {                                    // 如果不连通则 连通
                union(pre, x, y);
            }
        }
        // 统计集合数量
        for (int i = 0; i < pre.length; i++) {
            // 自己是祖先的则是一个集合
            if (pre[i] == i) setCount++;
        }
        // 如果剩余网线数量 少于 集合（已经连通的电脑）数量-1，则不成立，否则只需要拔下 集合数量-1 的网线。
        return (setCount-1 <= remain) ? (setCount-1) : -1;
    }

    // 并
    private void union(int[] pre, int x, int y) {
        int fx = find(pre, x);
        int fy = find(pre, y);
        pre[fx] = fy;
    }
    // 查
    private int find(int[] pre, int index) {
        if (pre[index] != index) {
            pre[index] = find(pre, pre[index]);
        }
        return pre[index];
    }
}
