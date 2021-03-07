package algorithm;

public class CheckStraightLine_1232 {

    /**
     * 由斜率公式得
     * (y1-y0)/(x1-x0)==(yi-y0)/(xi-x0)
     * 防止除0，变换成相乘的形式
     * (y1-y0)*(xi-x0)==(x1-x0)*(yi-y0)
     * @param coordinates
     * @return
     */
    public static boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length <= 2) return true;
        for (int i = 2; i < coordinates.length; i++) {
            if ((coordinates[1][1] - coordinates[0][1]) * (coordinates[i][0] - coordinates[0][0]) !=
                    (coordinates[1][0] - coordinates[0][0]) * (coordinates[i][1] - coordinates[0][1]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkStraightLine(new int[][]{{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}}));
        System.out.println(checkStraightLine(new int[][]{{4,8},{-2,8},{1,8},{8,8},{-5,8},{0,8},{7,8},{5,8}}));
        System.out.println(checkStraightLine(new int[][]{{1,1},{2,2},{2,0}}));
    }
}
