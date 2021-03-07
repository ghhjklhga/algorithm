package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 567. 字符串的排列
 */
public class CheckInclusion_0567 {

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        // 记录两个字符串的字符出现个数
        int[] a1 = new int[26];
        int[] a2 = new int[26];
        // 获取s1 和 s2 前s1.length()个字符串的各个字符个数
        for (int i = 0; i < chars1.length; i++) {

            a1[chars1[i] - 'a']++;
            a2[chars2[i] - 'a']++;
        }
        // 判断是否相等
        if (checkTwoArrSame(a1, a2)) return true;
        // 窗口一个字符一个字符地滑动
        for (int i = chars1.length; i < chars2.length; i++) {
            // 加头 去尾
            a2[chars2[i]-'a']++;
            a2[chars2[i - chars1.length] - 'a']--;
            if (checkTwoArrSame(a1, a2)) return true;
        }

        return false;
    }
    // 判断a1 和 a2是否一样（两个字符串的字符个数）
    private static boolean checkTwoArrSame(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) return false;
        }
        return true;
    }

    public boolean checkInclusion2(String s1, String s2) {
        char[] pattern = s1.toCharArray();
        char[] text = s2.toCharArray();

        int pLen = s1.length();
        int tLen = s2.length();

        int[] pFreq = new int[26];
        int[] winFreq = new int[26];

        for (int i = 0; i < pLen; i++) {
            pFreq[pattern[i] - 'a']++;
        }

        int pCount = 0;
        for (int i = 0; i < 26; i++) {
            if (pFreq[i] > 0){
                pCount++;
            }
        }

        int left = 0;
        int right = 0;
        // 当滑动窗口中的某个字符个数与 s1 中对应相等的时候才计数
        int winCount = 0;
        while (right < tLen){
            if (pFreq[text[right] - 'a'] > 0 ) {
                winFreq[text[right] - 'a']++;
                if (winFreq[text[right] - 'a'] == pFreq[text[right] - 'a']){
                    winCount++;
                }
            }
            right++;

            while (pCount == winCount){
                if (right - left == pLen){
                    return true;
                }
                if (pFreq[text[left] - 'a'] > 0 ) {
                    winFreq[text[left] - 'a']--;
                    if (winFreq[text[left] - 'a'] < pFreq[text[left] - 'a']){
                        winCount--;
                    }
                }
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(checkInclusion("qff", "ifisnoskikfqzrmzlv"));
        System.out.println(checkInclusion("ab", "eidbaooo"));
    }
}
