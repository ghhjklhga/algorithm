package algorithm;

import java.util.stream.Stream;

/**
 * 402. 移掉K位数字:给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 */
public class RemoveKdigits_0402 {

    public static String removeKdigits(String num, int k) {
        if (num.length() == k) return "0";      // 如果删除和数据相同数位直接返回“0”
        StringBuilder sb = new StringBuilder(num);  // 生成StringBuilder操作字符
        for (int j=0; j<k; j++) {               // 循环k次
            int index1 = 0;                        // 标志位，用于记录要删除的字符位置
            for (int i=0; i<sb.length()-1; i++) {       // 遍历每个字符
                if (sb.charAt(i) > sb.charAt(i + 1)) {      // 当遇到比前一位小的，则退出循环，知道后一位比前一位大即停止
                    break;
                }
                index1 = i+1;                           // 记录第一个后一位比前一位大的下标
            }
            sb.delete(index1, index1 + 1);          // 删除下标字符
            while (sb.length() > 1 && sb.charAt(0)==48) sb.delete(0, 1);    // 删除更改后的首字符为“0”的字符
        }
        return sb.toString();
    }

    /**
     * 上面的简化版
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits2(String num, int k) {
        StringBuilder sb = new StringBuilder(num);
        for (int j=0,index1=0; j<k; j++,index1=0) {
            for (int i=0; (i<sb.length()-1) && (sb.charAt(i)<=sb.charAt(i+1)); i++) index1 = i+1;
            sb.delete(index1, index1+1);
            while (sb.length() > 1 && sb.charAt(0)==48) sb.delete(0, 1);
        }
        return sb.length()>0 ? sb.toString() : "0";
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits2("112", 1));
    }
}
