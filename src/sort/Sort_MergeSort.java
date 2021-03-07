package sort;

import java.util.Arrays;

/**
 * 归并排序，分而治之
 */
public class Sort_MergeSort {
    private static void sort(int[] arr, int start, int end) {
        if (start >= end) return;
        // 分成两半
        int middle = (start + end) / 2;
        // 前一半
        sort(arr, start, middle);
        // 后一半
        sort(arr, middle+1, end);
        // 两半合起来
        merge(arr, start, middle, end);
    }

    private static void merge(int[] arr, int start, int middle, int end){
        int i=start, j=middle+1, index=0;
        int[] t = new int[end-start+1];
        // 现将两边按大小弄到新数组
        while (i<=middle && j<=end) {
            if (arr[i] < arr[j]) t[index++] = arr[i++];
            else if (arr[i] >= arr[j]) t[index++] = arr[j++];
        }
        // 剩下的，无论是哪边有剩下的，都要放进数组，而且未放进数组的，肯定是有序的
        while (i <= middle) t[index++] = arr[i++];
        while (j <= end) t[index++] = arr[j++];
        // 覆盖到到原数组里
        System.arraycopy(t, 0, arr, start, t.length);
    }

    public static void main(String[] args) {
        int[] a = new int[]{5,4,3,2,1, 8,3,9,8,0};
        sort(a, 0, a.length-1);
        Arrays.stream(a).forEach(System.out::println);
    }
}
