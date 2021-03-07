package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 839. 相似字符串组
 */
public class NumSimilarGroups_0839 {

    public int numSimilarGroups(String[] strs) {
        UnionFind unionFind = new UnionFind(strs.length);
        for (int i = 0; i < strs.length; i++) {
            for (int j = i+1; j < strs.length; j++) {
                // 如果相似，则合并
                if (check(strs[i], strs[j])) unionFind.union(i, j);
            }
        }
        return unionFind.count;
    }

    /**
     * 判断是否相似
     */
    private boolean check(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            // 如果两个字符不相等，则保存字符下标
            if (s1.charAt(i) != s2.charAt(i)) list.add(i);
        }
        // 是否不相同的字符数量为0或2，或者两个字符不是交换，是则就是“相似”
        return list.size() == 0 || (list.size() == 2 && s1.charAt(list.get(0)) == s2.charAt(list.get(1)) && s1.charAt(list.get(1)) == s2.charAt(list.get(0)));
    }

    private class UnionFind {

        private int[] parent;
        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }
}
