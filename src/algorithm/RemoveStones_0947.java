package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 947. 移除最多的同行或同列石头
 */
public class RemoveStones_0947 {
    public static void main(String[] args) {
//        System.out.println(removeStones(new int[][]{new int[]{1, 2}, new int[]{1, 3}, new int[]{2, 3}, new int[]{2, 2}}));
//        System.out.println(removeStones(new int[][]{new int[]{1, 0}}));
        System.out.println(removeStones(new int[][]{new int[]{0,0},new int[]{0,1},new int[]{1,0},new int[]{1,2},new int[]{2,1},new int[]{2,2}}));
    }


    public static int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones) {
            // 下面这三种写法任选其一（都是为了区分x轴和y轴，否则因为Map的key只有一个）
            // unionFind.union(~stone[0], stone[1]);
            // unionFind.union(stone[0] - 10001, stone[1]);
            unionFind.union(stone[0] + 10001, stone[1]);
        }
        return stones.length - unionFind.getCount();
    }
}

class UnionFind {
    private Map<Integer, Integer> parent;
    private int count;

    public UnionFind() {
        this.parent = new HashMap<>();
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public int find(int x) {
        if (!parent.containsKey(x)) {
            parent.put(x, x);
            // 并查集集中新加入一个结点，结点的父亲结点是它自己，所以连通分量的总数 +1
            count++;
        }
        if (x != parent.get(x)) {   // 顺便路径压缩
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    public void union(int x, int y) {
        // 降维，就是相当于两个一个点之间的xy之间看成一条连线。
        int rootX = find(x);
        int rootY = find(y);
        // 如果xy两头都一以连通，则无需再相连，否则有环，而且数量也无需-1，因为上面查找也没有加+（key都存在）
        if (rootX == rootY) return;
        // 头尾相连
        parent.put(rootX, rootY);
        // 两个连通分量合并成为一个，连通分量的总数 -1
        count--;
    }
}
