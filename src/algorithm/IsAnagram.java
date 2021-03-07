package algorithm;

import java.util.Arrays;
import java.util.stream.Collector;

/**
 * 有效的字母异位词
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * s = "anagram", t = "nagaram" true
 * s = "rat", t = "car"         false
 */
public class IsAnagram {
    /**
     * 标记法，先加后减，判断最后每个标记为是否都为0
     * 执行用时：5ms，击败了48.43的java提交用户
     * @param s
     * @param t
     * @return
     */
    private static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] x = new int[26];
        for (int i = 0; i < s.length(); i++) {
            x[s.charAt(i)-97] += 1;
            x[t.charAt(i)-97] -= 1;
        }
        for (int i : x) if (i != 0) return false;
        return true;
    }

    /**
     * 排序法
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.91%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了58.05%的用户
     * @param s
     * @param t
     * @return
     */
    private static boolean isAnagram2(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return String.valueOf(sChars).equals(String.valueOf(tChars));
    }

    public static void main(String[] args) {
        System.out.println(1<<16);
    }

}
