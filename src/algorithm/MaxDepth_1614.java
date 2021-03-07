package algorithm;

/**
 * 1614. 括号的最大嵌套深度
 */
public class MaxDepth_1614 {

    public int maxDepth(String s) {
        int max = 0;
        for (int i = 0, t = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') t++;
            else if (s.charAt(i) == ')') t--;
            max = Math.max(t, max);
        }
        return max;
    }
}
