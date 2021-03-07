package algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 距离顺序排列矩阵单元格
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 *
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 *
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 */
public class AllCellsDistOrder_1030 {
    /**
     * 思路：先拿到所有位置的坐标，再计算每个位置的曼哈顿距离，最后排序
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R*C][2];
        for(int i = 0,index = 0; i < R ; i ++)
            for(int j = 0 ; j < C ; j ++) {
                int[] xy = {i,j};
                res[index++] = xy;
            }
        Arrays.sort(res, (o1, o2) -> Math.abs(o1[0]-r0)+Math.abs(o1[1]-c0) - Math.abs(o2[0]-r0)+Math.abs(o2[1]-c0));
        return res;
    }

    /**
     * 全lambda写法，但是速度更慢
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        return IntStream.range(0, R).boxed()
                .flatMap(r -> IntStream.range(0, C).boxed().map(c -> new int[]{r, c}))
                .sorted(Comparator.comparingInt(a -> Math.abs(a[0] - r0) + Math.abs(a[1] - c0)))
                .collect(Collectors.toList())
                .toArray(new int[][]{});
    }
}
