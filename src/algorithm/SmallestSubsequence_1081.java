package algorithm;

import java.util.Stack;

/**
 * 不同字符的最小子序列
 *
 * 返回字符串 text 中按字典序排列最小的子序列，该子序列包含 text 中所有不同字符一次。
 */
public class SmallestSubsequence_1081 {

    public static String smallestSubsequence(String s) {
        Stack<Character> stack = new Stack<>();
        // 遍历数组
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            // 如果字符在栈中了就不取
            if(stack.contains(c)) continue;
            // 如果不在栈中，就跟栈顶的逐个比较大小，如果比栈顶小并且栈顶在后面还有重复的字符，那就后面再取
            while(!stack.isEmpty() && s.indexOf(stack.peek(),i) != -1 && stack.peek() > c){
                stack.pop();
            }
            stack.push(c);
        }
        // 栈内元素组成新的结果
        char[] res = new char[stack.size()];
        for(int i = stack.size()-1;i>=0;i--){
            res[i] = stack.pop();
        }
        return new String(res);
    }

    public static void main(String[] args) {
        System.out.println(smallestSubsequence("zabz"));
    }

    /**
     * 双一百
     * @param s
     * @return
     */
    public String smallestSubsequence2(String s) {
        //单调栈，用数组代替栈
        char[] stack = new char[26];
        //栈中是否含有指定字母
        boolean[] used = new boolean[26];
        //每种字母剩余的个数
        int[] cnt = new int[26];
        //栈顶指针
        int top = 0;

        //统计各字母的初始个数
        char[] sc = s.toCharArray();
        for(char c : sc) {
            cnt[c-'a']++;
        }

        for(int i = 0; i < sc.length; i++) {
            //栈中尚未含有当前遍历到的字母
            if(!used[sc[i]-'a']) {
                //当栈不为空，且违反了单调不减原则，且栈顶字母还有剩余时，出栈
                while(top>0 && stack[top-1]>sc[i] && cnt[stack[top-1]-'a']>0) {
                    //修改指定字母的used变量
                    used[stack[top-1]-'a'] = false;
                    //修改栈指针
                    top -= 1;
                }
                //上述任一条件不满足则将当前遍历到的字母入栈并修改相应的used变量
                stack[top++] = sc[i];
                used[sc[i]-'a'] = true;
            }
            //没遍历一个字母，就修改该字母的剩余个数
            cnt[sc[i]-'a']--;
        }

        //遍历栈中字母返回结果
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < top; i++) {
            sb.append(stack[i]);
        }
        return sb.toString();
    }
}
