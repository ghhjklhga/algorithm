package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 */
public class IsIsomorphic_0205 {
    /**
     * 暴力
     * 执行用时：1330 ms, 在所有 Java 提交中击败了5.06%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了83.53%的用户
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] a = new int[s.length()];
        int[] b = new int[s.length()];
        for (int i = 0; i < a.length; i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            a[i] = sc;
            b[i] = tc;
            for (int j = i - 1; j >= 0; j--) if ((a[j] != sc && tc == b[j]) || (a[j] == sc && tc != b[j])) return false;
        }
        return true;
    }

    /**
     * 通过hashmap，key-s.charAt(i)，value-t.charAt(i)
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic3(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {            // 如果map没有当前字符s的key
                // 则找是否value含有t字符value
                if (map.containsValue(t.charAt(i))) return false;
                // 保存当前字符
                map.put(s.charAt(i), t.charAt(i));
            } else {                                        // 如果map已经有当前s字符key
                if (map.get(s.charAt(i)) != t.charAt(i)) return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isIsomorphic3("foo", "bar"));
    }
}
