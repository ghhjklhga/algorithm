package algorithm;

/**
 * 424. 替换后的最长重复字符
 */
public class CharacterReplacement_0424   {

    public int characterReplacement(String s, int k) {
        // 记录每个字母的最大长度
        int[] num = new int[26];
        // 窗口内相同元素的最大个数
        int maxn = 0;
        // 左指针、右指针
        int left = 0, right = 0;
        while (right < s.length()) {
            num[s.charAt(right) - 'A']++;
            // 重点：这里是记录区间内最大的相同字符长度，其余的用k填补
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            // 如果不满足（k长度 + 相同子串长度 = 最大窗口长度），左指针右移一步，保持区间长度不变
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        // 区间长度
        return right - left;
    }
}
