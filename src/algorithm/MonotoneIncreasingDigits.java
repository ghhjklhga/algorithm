package algorithm;

/**
 * 单调递增的数字
 *
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 */
public class MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int N) {
        int i = 1;
        int res = N;
        while(i <= res/10) {
            int n = res / i % 100; // 每次取两个位
            i *= 10;
            if(n/10 > n%10) // 比较的高一位大于底一位
                res = res / i * i - 1; //例如1332 循环第一次变为1330-1=1329 第二次变为1300-1=1299
        }
        return res;
    }

    public int monotoneIncreasingDigits2(int N) {
        for (int i = N, j = 9, k = 1; i > 0; i /= 10, k *= 10)
            if (j < (j = i % 10))// 如果后一位比前一位小
                // 以332为例，第1次走到这一步的时候 i=33,k=10, 329进入递归
                // 第2次走到这一步的时候 i=3,k=100, 299进入递归
                return monotoneIncreasingDigits(i * k - 1);
        // 299递归出口
        return N;
    }

    public static void main(String[] args) {

    }
}
