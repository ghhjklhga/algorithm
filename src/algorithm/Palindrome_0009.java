package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Palindrome_0009 {
    /**
     * 非字符串做法，现将前一半放到栈里，再在后一半，每一个跟栈pop的相同则为true，否则false
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        Stack<Integer> stack = new Stack<>();
        int multiplier = 1;
        while (x / multiplier > 0) {
            if (x / multiplier /multiplier < 10 && x / multiplier /multiplier > 0) {    // 单双数的区别，例如121的2不要
                multiplier *= 10;
            }
            if (x / multiplier /multiplier == 0){
                if (x / multiplier % 10 == stack.pop()) {
                    if (multiplier == 1000000000) break;    // 因为要用到除数，大于10位再乘10就会超出Integer.MaxInt,也可以换成long
                    multiplier *= 10;
                    continue;
                } else
                    return false;
            }
            stack.push(x / multiplier % 10);
            multiplier *= 10;
        }
        return x / (multiplier / 10) > 0;
    }

    /**
     * 大伙做法，设定y，每次x / 10 则y * 10 + x % 10，判断结果是否一致。简介
     * @param x
     */
    public static boolean isPalindrome2(int x) {
        if(x<0)
            return false;
        int rem=0,y=0;
        int quo=x;
        while(quo!=0){
            rem=quo%10;
            y=y*10+rem;
            quo=quo/10;
        }
        return y==x;
    }

    /**
     * 栈和队列
     * @param x
     */
    public static boolean isPalindrome3(int x) {
        if (x < 0) return false;
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        for (; x>0; x/=10) {
            stack.push(x % 10);
            queue.offer(x % 10);
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != queue.poll()) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(123123123));
    }
}
