package algorithm;

import java.util.Arrays;

public class LargestPerimeter {
    /**
     * 排序法，再取最后三个
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        if (A.length < 3 || (A.length==3 && !check(A[0], A[1], A[2]))) return 0;
        Arrays.sort(A);
        for (int i=A.length-1; i>=2; i--) if (check(A[i], A[i-1], A[i-2])) return A[i]+A[i-1]+A[i-2];
        return 0;
    }
    private boolean check(int x, int y, int z) {
        return x+y>z && x+y>z && y+z>x;
    }

    public static void main(String[] args) {
        System.out.println(new LargestPerimeter().largestPerimeter(new int[]{3,4,15,2,9,4}));
    }
}
