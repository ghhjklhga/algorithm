package algorithm;

/**
 * 459. 重复的子字符串
 *
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 */
public class RepeatedSubstringPattern_0459 {
    public static boolean repeatedSubstringPattern(String s) {
        if (s.length() <= 1) return false;
        for (int i = 1; i <= (s.length()+1)/2; i++) {
            String substring = s.substring(0, i);
            int j;
            for (j = i; j+i <= s.length(); j+=i) {
                if (!s.substring(j, j+i).equals(substring)) break;
            }
            if (j == s.length()) return true;
        }
        return false;
    }

    public static boolean repeatedSubstringPattern2(String s) {
        int len = s.length();
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < len / 2; i++) {
            pattern.append(s.charAt(i));
            // 当前子串长度是原字符串的整数倍且成功匹配
            if (len % (i + 1) == 0 && s.matches("(" + pattern.toString() + ")+")) return true;
        }
        return false;
    }

    public boolean repeatedSubstringPattern3(String s) {
        return (s + s).substring(1, (s + s).length() - 1).contains(s);
    }

    public String reverseLeftWords(String s, int n) {
//        return s.substring(n, s.length()) + s.substring(0, n);
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < chars.length; i++) {
            sb.append(chars[i]);
        }
        for (int i = 0; i < n; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abab"));
    }
}
