package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LegalBrackets_0020 {
    /*
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * @param s
     * @return
     */

    public static boolean isValid(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(40, 41);
        map.put(91, 93);
        map.put(123, 125);
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            if (!stack.isEmpty() && map.get(stack.peek())!=null && map.get(stack.peek())==s.charAt(i)) stack.pop();
            else stack.push((int) s.charAt(i));
        }
        return stack.isEmpty();
    }

    /**
     * 遇到 左边 push右边，当遇到右边的一定等于上一次push的，否则false，最后stack不为空也是false
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char c:s.toCharArray()){
            if(c=='(')stack.push(')');
            else if(c== '{')stack.push('}');
            else if(c=='[')stack.push(']');
            else if(stack.isEmpty() || c!=stack.pop())return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));
    }
}
