package algorithm;

public class Rotate {
    /**
     * 先对角后镜像
     *
     * 需要注意的是，这里使用的是先左下右上的对角线翻转，然后是上下的镜面翻转，如果是左上右下对角线的应该对应回左右镜面翻转
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        // 先对角线反转
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[length -j-1][length -i-1];
                matrix[length -j-1][length -i-1] = t;
            }
        }
        // 后镜像
        for (int i = 0; i < length /2; i++) {
            for (int j = 0; j < length; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[length -i-1][j];
                matrix[length -i-1][j] = t;
            }
        }
    }

    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
}
