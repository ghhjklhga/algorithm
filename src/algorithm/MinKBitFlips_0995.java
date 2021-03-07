package algorithm;

import java.util.Deque;
import java.util.LinkedList;

public class MinKBitFlips_0995 {

    public int minKBitFlips(int[] A, int K) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != 1) {
                if (i + K > A.length) return -1;
                reverse(A, i, i + K);
                count++;
            }
        }
        return count;
    }

    private void reverse(int[] a, int i, int j) {
        for (; i < j; i++) {
            a[i] = a[i]==1 ? 0 : 1;
        }
    }

    public int minKBitFlips2(int[] A, int K) {
        int res = 0;
        Deque<Integer> que = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            // 如果当前数已经超过了窗口，把开头的去掉
            if (que.size() > 0 && i > que.peek() + K - 1) {
                que.removeFirst();
            }
            // 这个if包含两种情况
            // 1.本来是1，翻转奇数次变为0，所以需要再次翻转，放入队列
            // 2.本来是0，翻转偶数次还是0，所以需要再次翻转，放入队列
            if (que.size() % 2 == A[i]) {
                // 如果已经超过界限，不成立
                if (i + K > A.length) return -1;
                que.add(i);
                res++;
            }
        }
        return res;
    }
}
