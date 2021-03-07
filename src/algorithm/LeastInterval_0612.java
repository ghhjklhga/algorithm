package algorithm;

import java.util.Arrays;

/**
 * 任务调度器
 *
 * 你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 *
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的 最短时间 。
 *
 */
public class LeastInterval_0612 {
    /**
     * 先用数组把26个字母的出现次数记录下来，每次循环之前排序，把最多的放到前面（这样肯定不是最优解）
     * 执行用时：16 ms, 在所有 Java 提交中击败了25.59%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了68.24%的用户
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval(char[] tasks, int n) {
        if (n==0) return tasks.length;
        int count=0, index=tasks.length;
        Integer[] a = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (char task : tasks) a[task-'A']++;

        while (index > 0) {
            index = 0;
            int x = n + 1;
            Arrays.sort(a, (o1, o2) -> (o2 - o1));
            for (int i=0; i<26; i++) {
                if (x > 0 && a[i] > 0) {
                    count++;
                    a[i]--;
                    x--;
                }
                index += a[i];
            }
            if (index > 0) count += x;
        }
        return count;
    }

    /**
     * 我草牛逼，主要是最后那条公式
     * 公式解析
     * 假设数组 ["A","A","A","B","B","C"]，n = 2，A的频率最高，记为count = 3，所以两个A之间必须间隔2个任务，才能满足题意并且是最短时间（两个A的间隔大于2的总时间必然不是最短），因此执行顺序为： A->X->X->A->X->X->A，这里的X表示除了A以外其他字母，或者是待命，不用关心具体是什么，反正用来填充两个A的间隔的。上面执行顺序的规律是： 有count - 1个A，其中每个A需要搭配n个X，再加上最后一个A，所以总时间为 (count - 1) * (n + 1) + 1
     * 要注意可能会出现多个频率相同且都是最高的任务，比如 ["A","A","A","B","B","B","C","C"]，所以最后会剩下一个A和一个B，因此最后要加上频率最高的不同任务的个数 maxCount
     * 公式算出的值可能会比数组的长度小，如["A","A","B","B"]，n = 0，此时要取数组的长度
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval2(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) count[task - 'A']++;    //统计词频
        Arrays.sort(count); //词频排序，升序排序，count[25]是频率最高的
        int maxCount = 0;
        for (int i = 25; i >= 0; i--) { //统计有多少个频率最高的字母
            if(count[i] != count[25]) break;
            maxCount++;
        }
        //公式算出的值可能会比数组的长度小，取两者中最大的那个
        return Math.max((count[25] - 1) * (n + 1) + maxCount , tasks.length);
    }

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A','A','B','B','C','C','D','D','E','E','F','F','G','G','H','H','I','I','J','J','K','K','L','L','M','M','N','N','O','O','P','P','Q','Q','R','R','S','S','T','T','U','U','V','V','W','W','X','X','Y','Y','Z','Z'}, 2));
//        System.out.println(leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
    }
}
