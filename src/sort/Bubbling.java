package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * <p>
 * 每次从0开始，i 遇到 比他小的 i+1 ，就交换位置，知道 length-i-1 位
 * 把第 i 次的前 length-i 个数中的maxVal 放到 第length-i-1位
 * <p>
 * 这他妈根本就是直接插入的巴黎铁塔翻转再翻转，只不过这个的最终位置是固定的，而插入点到即止，讲码德。
 */
public class Bubbling {

    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 4, 6, 7, 1, 4, 5, 6, 0};
        sort(a);
        Arrays.stream(a).forEach(System.out::print);
        System.out.println();
    }
}
