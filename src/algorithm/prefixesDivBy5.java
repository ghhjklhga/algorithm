package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 1018. 可被 5 整除的二进制前缀
 */
class PrefixesDivBy5_1018 {

    /**
     * 超长
     * @param A
     * @return
     */
    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        int t = 0;
        for (int aA : A) {
            t = (t << 1) + aA;
            t %= 10;
            list.add(t % 5 == 0);
        }
        return list;
    }

    public List<Boolean> prefixesDivBy5_1(int[] A) {
        List<Boolean> ans = new ArrayList<>();
        int num = 0;
        for (int i = 0;i < A.length;i++) {
            num <<= 1;
            num += A[i];
            num %= 10;
            ans.add(num % 5 == 0);
        }

        return ans;
    }


    public static void main(String[] args) {
        System.out.println(prefixesDivBy5(new int[]{0,1,1,1,1,1}));
    }
}
