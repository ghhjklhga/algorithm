package algorithm;

/**
 * 1266. 访问所有点的最小时间
 */
public class MinTimeToVisitAllPoints_1266 {

    public int minTimeToVisitAllPoints(int[][] points) {
        if (points.length < 2) return 0;
        int sum = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int x = Math.abs(points[i + 1][0] - points[i][0]);
            int y = Math.abs(points[i + 1][1] - points[i][1]);
            sum += Math.max(x, y);
        }
        return sum;
    }
}
