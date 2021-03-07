package algorithm;

import java.util.Arrays;

/**
 * 最长公共前缀
 */
public class LongestCommonPrefix_0014 {
    /**
     * 先排序，拿第一个最短的字符串为基准长度（只为不判断越界）
     * 执行用时：7 ms, 在所有 Java 提交中击败了11.61%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了87.97%的用户
     * 太菜了！
     *
     * 把排序去掉
     * 执行用时：1 ms, 在所有 Java 提交中击败了88.45%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了91.14%的用户
     * wdnmd
     * @param strs
     * @return
     */
    private static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        StringBuilder sb = new StringBuilder();
//        String[] arrs = Arrays.stream(strs).sorted((a, b) -> (a.length()-b.length())).toArray(String[]::new); 加上这个得加上排序的时间，重者O(n²)。。。
        for (int i=0, j=1; i<strs[0].length(); i++,j=1) {
            char c = strs[0].charAt(i);
            for (; j < strs.length; j++) if (i>strs[j].length()-1 || c != strs[j].charAt(i)) break;
            if (j != strs.length) return sb.toString();
            sb.append(String.valueOf(Character.valueOf(c)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"ab","a"}));;
    }
}
