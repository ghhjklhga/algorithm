package algorithm;

import java.util.Arrays;

public class NoSameString_0003 {
    public static int lengthOfLongestSubstring(String s) {
        // 记录字符上一次出现的位置
        int[] last = Arrays.stream(new int[128]).map(a -> a = -1).toArray();
//        for(int i = 0; i < 128; i++) {
//            last[i] = -1;
//        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);  // 每遇到重复就重新定位start，即窗口开始位置
            res   = Math.max(res, i - start + 1);   // 然后通过上面定位的当前位置，取历史最大的窗口
            last[index] = i;                        // 标志该位置，来确定下一个窗口start位置
        }
        return res;

        /**
         * abcabcbb，当i等于3时，也就是指向了第二个a, 此时我就需要查之前有没有出现过a, 如果出现了是在哪一个位置出现的。
         * 然后通过last[index] 查到等于1, 也就是说，如果start 依然等于0的话，那么当前窗口就有两个a了，也就是字符串重复了，
         * 所以我们需要移动当前窗口的start指针，移动到什么地方呢？移动到什么地方，窗口内就没有重复元素了呢？
         * 对了，就是a上一次出现的位置的下一个位置，就是1 + 1 = 2。当start == 2, 当前窗口就没有了重复元素，
         * 那么以当前字符为结尾的最长无重复子串就是bca,然后再和之前的res取最大值。然后i指向后面的位置，按照同样思路计算。
         */
    }

    public int lengthOfLongestSubstring2(String s) {
        int[] charStartIndex = Arrays.stream(new int[128]).map(a -> a = -1).toArray();
        int start = 0, maxLength = 0;
        for (int i=0; i<s.length(); i++) {
            int charInt = s.charAt(i);
            start = Math.max(start, charStartIndex[charInt] + 1);
            maxLength = Math.max(maxLength, i - start + 1);
            charStartIndex[charInt] = i;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("asdasdss"));
    }
}
