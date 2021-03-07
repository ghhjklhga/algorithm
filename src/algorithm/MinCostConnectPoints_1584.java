package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1584. 连接所有点的最小费用
 */
public class MinCostConnectPoints_1584 {

    /**
     * prim算法
     *
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        if (points.length < 2) return 0;
        int checkPoint = 0;
        int cost = 0;
        int[] lowcost = new int[points.length];
        Arrays.fill(lowcost, Integer.MAX_VALUE);
        lowcost[0] = 0;
        //循环n-1次
        for (int i = 0; i < points.length - 1; i++) {
            //计算其他结点到生成树的距离
            int minDist = Integer.MAX_VALUE;
            int temp = checkPoint;
            for (int v = 0; v < points.length; v++) {
                //lowcost[v]==0则表示已经加入到生成树中了
                if (lowcost[v] > 0) {
                    //计算其他点到生成树的距离
                    lowcost[v] = Math.min(lowcost[v], manhaton(points, v, checkPoint));
                    //选择当前最短的距离作为新的检查点
                    if (lowcost[v] < minDist) {
                        minDist = lowcost[v];
                        temp = v;
                    }
                }
            }
            //更新检查点
            checkPoint = temp;
            //将新的检查点放入最小生成树
            lowcost[checkPoint] = 0;
            //更新总费用
            cost += minDist;
        }
        return cost;
    }

    // 曼哈顿距离
    private int manhaton(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }


    public int minCostConnectPoints2(int[][] points) {
        int n = points.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        List<Edge> edges = new ArrayList<>();
        // 计算每个点 到 其他各个点 的 曼哈顿距离
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(manhaton(points, i, j), i, j));
            }
        }
        // 把所有边 按照曼哈顿距离从小到大排序
        edges.sort(Comparator.comparingInt(edge2 -> edge2.len));
        int ret = 0, num = 1;
        // 计算两个点是否连通，不连通则加入集合，连通则已成回路
        for (Edge edge : edges) {
            int len = edge.len, i = edge.i, j = edge.j;
            // 判断两个点 是否已经连通，连通则加上距离
            if (dsu.unionSet(i, j)) {
                ret += len;
                num++;
                if (num == n) break;
            }
        }
        return ret;
    }
}

class DisjointSetUnion {
    int[] f;    // 记录父节点
    int[] rank; // 记录根节点的深度（用于优化，路径压缩引入后这个名称不准确）
    int n;

    public DisjointSetUnion(int n) {
        this.n = n;
        this.rank = new int[n];
        Arrays.fill(this.rank, 1);
        this.f = new int[n];
        for (int i = 0; i < n; i++) {
            this.f[i] = i;
        }
    }

    public int find(int i) {
        return f[i] == i ? i : (f[i] = find(f[i]));
    }

    public boolean unionSet(int i, int j) {
        int fi = find(i), fj = find(j);
        // 这两个点 已连通。
        if (fi == fj) {
            return false;
        }
        // 维护并查集树形结构，保证高度为log(n)，不会是一颗很长很长的单列树
        if (rank[fi] < rank[fj]) {
            int temp = fi;
            fi = fj;
            fj = temp;
        }
        rank[fi] += rank[fj];
        // 连通
        f[fj] = fi;
        return true;
    }
}

class Edge {
    int len, i, j;

    public Edge(int len, int i, int j) {
        this.len = len;
        this.i = i;
        this.j = j;
    }
}
