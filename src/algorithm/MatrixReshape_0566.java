package algorithm;

public class MatrixReshape_0566 {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length * nums[0].length != r * c) return nums;
        int[][] a = new int[r][c];
        for (int i = 0, row = 0, col = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (col == c) {
                    col = 0;
                    row++;
                }
                a[row][col++] = nums[i][j];
            }
        }
        return a;
    }
}
