package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 */
public class RomanToInt_0013 {
    /**
     * 思路：其实如果知道罗马数字的规律，左边一个小于右边一个必减、大必加，水到渠成
     *
     * 执行用时：7 ms, 在所有 Java 提交中击败了40.48%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了83.11%的用户
     * @param s
     * @return
     */
    private static int romanToInt(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put((int) 'I', 1);
        map.put((int) 'V', 5);
        map.put((int) 'X', 10);
        map.put((int) 'L', 50);
        map.put((int) 'C', 100);
        map.put((int) 'D', 500);
        map.put((int) 'M', 1000);

        int sum = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i+1<chars.length && map.get((int) chars[i])<map.get((int) chars[i+1])) sum -= map.get((int) chars[i]);
            else sum += map.get((int) chars[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
