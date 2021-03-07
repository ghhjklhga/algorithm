package algorithm;

import java.util.Arrays;

/**
 * 上升下降字符串
 *
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 *
     * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
     * 重复步骤 2 ，直到你没法从 s 中选择字符。
     * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
     * 重复步骤 5 ，直到你没法从 s 中选择字符。
     * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
     * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 *
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 */
public class SortString {
    /**
     * 记录下标方式
     * @param s
     * @return
     */
    public static String sortString(String s) {
        int[] ints = new int[26];
        // 下标数组，记录个数
        for (int i=0; i<s.length(); i++) ints[s.charAt(i)-97]++;
        StringBuilder sb = new StringBuilder();
        // 来回循环填充
        int count;
        do {
            count = 0;
            // 正序
            for (int i=0; i<ints.length; i++) {
                if (ints[i] > 0) {
                    sb.append((char)(i+97));
                    count++;
                    ints[i]--;
                }
            }
            // 逆序
            for (int i=ints.length-1; i>=0; i--) {
                if (ints[i] > 0) {
                    sb.append((char)(i+97));
                    count++;
                    ints[i]--;
                }
            }
        } while (count > 0);
        return sb.toString();
    }


    /**
     * 排序方式
     * @param s
     * @return
     */
    public static String sortString2(String s) {
        char[] chars = s.toCharArray();
        // 排序chars
        Arrays.sort(chars);
        // 下标数组，记录个数
        StringBuilder sb = new StringBuilder();
        // 来回循环填充
        int count;
        do {
            count = 0;  // 标记
            // 正序
            for (int i=0; i<chars.length; i++) {
                // if啥啥啥
                // append(chars[i]
            }
            // 逆序
            for (int i=chars.length-1; i>=0; i--) {
                // if啥啥啥
                // append(chars[i]
            }
        } while (count > 0);
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(sortString("aaaabbbbcccc"));
    }
}
