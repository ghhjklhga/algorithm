package algorithm;

public class DiagonalSum_1572 {

    /**
     * 1572. 矩阵对角线元素的和
     * @param mat
     * @return
     */
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        for (int i = 0; i < mat.length; i++) sum += ((mat.length%2==1 && i==mat.length/2) ? mat[i][i] : mat[i][i] + mat[i][mat.length-i-1]);
        return sum;
    }
}
