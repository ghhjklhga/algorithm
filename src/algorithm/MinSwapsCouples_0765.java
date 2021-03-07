package algorithm;

/**
 * 765. 情侣牵手
 */
public class MinSwapsCouples_0765 {

    /**
     * 这里的并查集使用了路径压缩，但是没有使用按秩合并，最坏情况下的时间复杂度是O(NlogN)，
     * 平均情况下的时间复杂度依然是 O(Nα(N))，其中α 为阿克曼函数的反函数，α(N) 可以认为是一个很小的常数。
     */
    public static int minSwapsCouples(int[] row) {
        UnionFindSet unionFindSet = new UnionFindSet(row.length / 2);
        for (int i = 0; i < row.length; i+=2) {
            // 每两个座位检测一次是否需要交换
            unionFindSet.union(row[i] / 2, row[i+1] / 2);
        }

        return unionFindSet.getCount();
    }

    public static void main(String[] args) {
        System.out.println(minSwapsCouples(new int[]{3, 2, 0, 1}));
    }
}

class UnionFindSet {
    int count;
    int[] f;

    public UnionFindSet(int n) {
        this.count = 0;
        f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
    }

    public int find(int x) {
        int fx = f[x];
        // 路径压缩
        if (fx != f[fx]) f[x] = find(fx);

        return f[x];
    }

    public void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);

        // 合并，交换次数+1
        if (fx != fy) {
            f[fx] = fy;
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
