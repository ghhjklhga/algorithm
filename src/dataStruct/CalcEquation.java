package dataStruct;

import java.util.*;

/**
 * 399. 除法求值
 */
public class CalcEquation {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int count = 0;
        //统计出现的所有字符，并赋予对应的index
        Map<String, Integer> map = new HashMap<>();
        for (List<String> list : equations) {
            for (String s : list) {
                if (!map.containsKey(s)) {
                    map.put(s, count++);
                }
            }
        }

        //构建一个矩阵来代替图结构
        double[][] graph = new double[count + 1][count + 1];

        //初始化
        for (String s : map.keySet()) {
            int x = map.get(s);
            graph[x][x] = 1.0;
        }
        int index = 0;
        for (List<String> list : equations) {
            String a = list.get(0);
            String b = list.get(1);
            int aa = map.get(a);
            int bb = map.get(b);
            double value = values[index++];
            graph[aa][bb] = value;
            graph[bb][aa] = 1 / value;
        }

        //通过Floyd算法进行运算
        int n = count + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k || graph[j][k] != 0) continue;
                    if (graph[j][i] != 0 && graph[i][k] != 0) {
                        graph[j][k] = graph[j][i] * graph[i][k];
                    }
                }
            }
        }

        //直接通过查询矩阵得到答案
        double[] res = new double[queries.size()];
        for (int i = 0; i < res.length; i++) {
            List<String> q = queries.get(i);
            String a = q.get(0);
            String b = q.get(1);
            if (map.containsKey(a) && map.containsKey(b)) {
                double ans = graph[map.get(a)][map.get(b)];
                res[i] = (ans == 0 ? -1.0 : ans);
            } else {
                res[i] = -1.0;
            }
        }
        return res;
    }

    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars = 0;
        Map<String, Integer> variables = new HashMap<>();

        int n = equations.size();
        for (List<String> equation : equations) {
            if (!variables.containsKey(equation.get(0))) {
                variables.put(equation.get(0), nvars++);
            }
            if (!variables.containsKey(equation.get(1))) {
                variables.put(equation.get(1), nvars++);
            }
        }

        // 对于每个点，存储其直接连接到的所有点及对应的权值
        List<Pair>[] edges = new List[nvars];
        for (int i = 0; i < nvars; i++) {
            edges[i] = new ArrayList<Pair>();
        }
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            edges[va].add(new Pair(vb, values[i]));
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }

        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                if (ia == ib) {
                    result = 1.0;
                } else {
                    Queue<Integer> points = new LinkedList<Integer>();
                    points.offer(ia);
                    double[] ratios = new double[nvars];
                    Arrays.fill(ratios, -1.0);
                    ratios[ia] = 1.0;

                    while (!points.isEmpty() && ratios[ib] < 0) {
                        int x = points.poll();
                        for (Pair pair : edges[x]) {
                            int y = pair.index;
                            double val = pair.value;
                            if (ratios[y] < 0) {
                                ratios[y] = ratios[x] * val;
                                points.offer(y);
                            }
                        }
                    }
                    result = ratios[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    // 构造一个map用于存储图
    Map<String, List<Node>> map;

    public double[] calcEquation3(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        map = new HashMap<>();
        double[] res = new double[queries.size()];

        // 将节点都放到图中
        for (int i = 0; i < n; i++) {
            // 获取被除数和除数的节点名称
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);

            // 如果map中不包含某个节点的字符串，那就添加一个键值对
            if (!map.containsKey(dividend)) {
                map.put(dividend, new ArrayList<>());
            }
            if (!map.containsKey(divisor)) {
                map.put(divisor, new ArrayList<>());
            }
            // 除数和被除数都要放进去，这是一个有向图
            // 除数和被除数的倍数值是倒数关系的
            map.get(dividend).add(new Node(divisor, values[i]));
            map.get(divisor).add(new Node(dividend, 1 / values[i]));
        }

        int cnt = 0;
        // 遍历问题
        for (List<String> q : queries) {
            // 深搜，初始倍数就是1
            res[cnt] = dfs(q.get(0), q.get(1), 1.0, new HashSet<>());
            cnt++;
        }

        return res;
    }

    // 深搜其中参数cur表示当前节点，dest表示目标节点，knerl表示之前计算的倍数，set保存已经走过的节点
    private double dfs(String cur, String dest, double knerl, Set<String> set) {
        // 如果map不包含当前的节点或者已经走过当前节点了，说明这条路不会产生答案
        if (!map.containsKey(cur) || set.contains(cur)) {
            return -1.0;
        }
        // 走到了终点，那就返回已经计算了的倍数
        if (cur.equals(dest)) return knerl;
        // 集合中添加当前走过的节点，防止绕圈
        set.add(cur);

        // 遍历当前节点的邻接节点
        for (Node node : map.get(cur)) {
            // 继续深搜，倍数需要乘上下个一个节点的倍数
            double temp = dfs(node.id, dest, knerl * node.num, set);
            // 如果搜到了答案，就直接返回答案
            if (temp != -1.0) return temp;
        }

        // 没有搜到答案，返回-1
        return -1.0;
    }

}

class Node {
    // 邻接节点代表的字符串
    public String id;
    // 到达邻接节点所需的倍数
    public double num;

    public Node(String i, double n) {
        id = i;
        num = n;
    }
}

class Pair {
    int index;
    double value;

    Pair(int index, double value) {
        this.index = index;
        this.value = value;
    }
}
