package sort;

import java.util.Arrays;

/**
 * 直接插入
 * <p>
 * i 要小于 0 到 i-1 之间所有。
 */
public class DirectlyInsert {

    // 原始
    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {    // 如果后一位 大于前一位，则交换位置
                    int t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                }
            }
        }
    }

    // 简洁
    public static void sort2(int[] a) {
        for (int i = 0, j; i < a.length; i++) {
            int t = a[i];
            // 前向移动，直到前一位比 当前值 小
            for (j = i; j > 0 && t < a[j - 1]; j--) a[j] = a[j - 1];
            // 当前值位置
            a[j] = t;
        }
    }

    // 插入优化 - 希尔排序
    public static void sort3(int[] a) {
        //希尔排序
        int gap = a.length;
        do {
            gap /= 2;   //增量每次减半
            for (int i = 0; i < gap; i++) {
                for (int j = i; j < a.length; j += gap) {//这个循环里其实就是一个插入排序
                    int k;
                    int t = a[j];
                    for (k = j; k - gap >= 0 && t < a[k - gap]; k -= gap) {
                        a[k] = a[k - gap];
                    }
                    a[k] = t;
                }
            }
        } while (gap != 1);
    }


    public static void main(String[] args) {
        int[] a = new int[]{2, 4, 6, 7, 1, 4, 5, 6, 0};
        sort2(a);
        Arrays.stream(a).forEach(System.out::print);
    }
}

