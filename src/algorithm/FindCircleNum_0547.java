package algorithm;

/**
 * 547. 省份数量
 */
public class FindCircleNum_0547 {

    public int findCircleNum(int[][] isConnected) {
        UnionFindSet unionFindSet = new UnionFindSet(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i+1; j < isConnected.length; j++) {
                // 每两个省份的连接情况，1的就连通。
                if (isConnected[i][j] == 1) unionFindSet.union(i, j);
            }
        }
        return unionFindSet.getCount();
    }


    private class UnionFindSet {
        int count;
        int[] p;

        public UnionFindSet(int n) {
            this.count = n;
            p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        public int getCount() {
            return count;
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) return;

            p[fx] = fy;
            count--;
        }

        private int find(int x) {
            if (x != p[x]) p[x] = find(p[x]);
            return p[x];
        }
    }

}
